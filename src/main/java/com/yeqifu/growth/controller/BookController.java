package com.yeqifu.growth.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeqifu.growth.entity.Allscore;
import com.yeqifu.growth.entity.Book;
import com.yeqifu.growth.service.IAllscoreService;
import com.yeqifu.growth.service.IBookService;
import com.yeqifu.growth.vo.BookVo;
import com.yeqifu.sys.common.AppFileUtils;
import com.yeqifu.sys.common.Constast;
import com.yeqifu.sys.common.DataGridView;
import com.yeqifu.sys.common.ResultObj;
import com.yeqifu.sys.entity.User;
import com.yeqifu.sys.service.IGradeService;
import com.yeqifu.sys.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.yeqifu.sys.common.WebUtils.getSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cxc-
 * @since 2022-02-18
 */
@RestController
@RequestMapping("/growth/book")
public class BookController {
    @Autowired
    private IAllscoreService allscoreService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IGradeService gradeService;
    @Autowired
    private IBookService bookService;
    /**
     * 添加学年模块成绩表单
     * @return
     */
    @RequestMapping("addBookScore")
    public ResultObj addbook(Book book){
        User user = (User) getSession().getAttribute("user");
        book.setUserid(user.getId());
        book.setYpass("2");
        //获取当前时间
        book.setBookdate(new Date());
        //防止增加数据库信息年份重复
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",user.getId());
        queryWrapper.eq("year",book.getYear());
        int count = bookService.count(queryWrapper);
        if(count>0){
            return ResultObj.MODULE_SCORE_REPEAT_ERROR;
        }else{
            try {
                if (book.getImgpath()!=null&&book.getImgpath().endsWith("_temp")){
                    String newName = AppFileUtils.renameFile(book.getImgpath());
                    book.setImgpath(newName);
                }
                bookService.save(book);
                return ResultObj.MODULE_SCORE_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                return ResultObj.MODULE_SCORE_ERROR;
            }
        }

    }
    /**
     * 查询学生个人提交记录
     * @param book
     * @return
     */
    @RequestMapping("loadBookScore")
    public DataGridView loadBook(Book book){
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        User user = (User) getSession().getAttribute("user");
        queryWrapper.eq("userid",user.getId());
        List<Book> list = bookService.list(queryWrapper);
        System.out.println(list+"===========================");
        return new DataGridView(list);
    }
    /**
     * 删除学生个人提交书籍成绩数据
     * @param id
     * @return
     */
    @RequestMapping("deleteBook")
    public ResultObj deleteBook(Integer id){
        try {
            bookService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 学生修改出版书籍信息
     * @param book
     * @return
     */
    @RequestMapping("updateBook")
    public ResultObj updateBook(Book book){
        User user = (User) getSession().getAttribute("user");
        book.setUserid(user.getId());
        book.setYpass("2");
        //获取当前时间
        book.setBookdate(new Date());
        try {
            //商品图片不是默认图片
            if (!(book.getImgpath()!=null&&book.getImgpath().equals(Constast.DEFAULT_IMG_GOODS))){

                if (book.getImgpath().endsWith("_temp")){
                    String newName = AppFileUtils.renameFile(book.getImgpath());
                    book.setImgpath(newName);
                    //删除原先的图片
                    String oldPath = bookService.getById(book.getId()).getImgpath();
                    AppFileUtils.removeFileByPath(oldPath);
                }
            }
            bookService.updateById(book);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 教师批量删除出版书籍提交记录
     * @param bookVo
     * @return
     */
    @RequestMapping("batchDeleteBook")
    public ResultObj batchDeleteBook(BookVo bookVo){
        try {
            Collection<Serializable> idList = new ArrayList<>();
            for (Integer id : bookVo.getIds()) {
                idList.add(id);
            }
            bookService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 查询所有学生提交记录
     * @param bookVo
     * @return
     */
    @RequestMapping("loadAllBookScore")
    public DataGridView loadAllBook(BookVo bookVo){
        //1.声明一个分页page对象
        IPage<Book> page = new Page<Book>(bookVo.getPage(),bookVo.getLimit());
        QueryWrapper<Book> queryWrapper = new QueryWrapper<Book>();
        queryWrapper.like(StringUtils.isNotBlank(bookVo.getYear()),"year",bookVo.getYear());
        queryWrapper.like(StringUtils.isNotBlank(bookVo.getYpass()),"ypass",bookVo.getYpass());
        //根据创建时间进行排序
        queryWrapper.orderByDesc("doscore");
        bookService.page(page,queryWrapper);
        //将所有数据放入list中
        List<Book> list = page.getRecords();
        //级联查询出学生（user)姓名，学号;(grade)年级
        for (Book book : list) {
            Integer studentId = book.getUserid();
            if (studentId!=null){
                //先从缓存中去取，如果缓存中没有就去数据库中取
                User student = userService.getById(studentId);
                //设置学生的姓名和学号
                book.setStudentname(student.getName());
                book.setSnumber(student.getLoginname());
                //设置学生年级
                book.setGrades(gradeService.getById(student.getGradeid()).getName());
            }
        }
        ArrayList<Book> books = new ArrayList<>();
        if(StringUtils.isNotBlank(bookVo.getGrades())&&StringUtils.isBlank(bookVo.getStudentname())){
            books.clear();
            for (Book book : list) {
                if(book.getGrades()!=null&&book.getGrades().equals(bookVo.getGrades())){
                    books.add(book);
                }
            }
            return new DataGridView(books.stream().count(),books);
        }
        if(StringUtils.isNotBlank(bookVo.getStudentname())&&StringUtils.isBlank(bookVo.getGrades())){
            books.clear();
            for (Book book : list) {
                if(book.getStudentname()!=null&&book.getStudentname().indexOf(bookVo.getStudentname())>=0){
                    books.add(book);
                }
            }
            return new DataGridView(books.stream().count(),books);
        }
        if(StringUtils.isNotBlank(bookVo.getStudentname())&&StringUtils.isNotBlank(bookVo.getGrades())){
            books.clear();
            for (Book book : list) {
                if(book.getStudentname()!=null&&book.getStudentname().indexOf(bookVo.getStudentname())>=0&&book.getGrades()!=null&&book.getGrades().equals(bookVo.getGrades())){
                    books.add(book);
                }
            }
            return new DataGridView(books.stream().count(),books);
        }
        return new DataGridView(page.getTotal(),page.getRecords());
    }
    /**
     * 审核通过
     * @param id
     * @return
     */
    @RequestMapping("updateVerify1")
    public ResultObj updateVerify1(Integer id,String snumber,String year,Double doscore){
        try {
            //审核通过设置
            Book book=bookService.getById(id);
            book.setYpass("1");
            bookService.updateById(book);
            //通过后更新综合成绩表单
            Integer userid = userService.getIdByLoginName(snumber);
            QueryWrapper<Allscore> allscoreQueryWrapper = new QueryWrapper<>();
            allscoreQueryWrapper.eq("userid",userid);
            allscoreQueryWrapper.eq("year",year);
            Allscore one = allscoreService.getOne(allscoreQueryWrapper);
            if(one==null){
                book.setYpass("2");
                bookService.updateById(book);
                return ResultObj.VERIFY_ERROR;
            }
            one.setKbook(doscore);
            allscoreService.updateById(one);
            return ResultObj.VERIFY_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ERROR;
        }
    }

    /**
     * 审核不通过
     * @param id
     * @return
     */
    @RequestMapping("updateVerify0")
    public ResultObj updateVerify0(Integer id,String snumber,String year,String why){
        try {
            //拒绝通过
            Book book=bookService.getById(id);
            book.setYpass("0");
            book.setWhy(why);
            bookService.updateById(book);
            //拒绝后查找综合成绩表单，对应成绩清零
            Integer userid = userService.getIdByLoginName(snumber);
            QueryWrapper<Allscore> allscoreQueryWrapper = new QueryWrapper<>();
            allscoreQueryWrapper.eq("userid",userid);
            allscoreQueryWrapper.eq("year",year);
            Allscore one = allscoreService.getOne(allscoreQueryWrapper);
            one.setKbook(0.00);
            allscoreService.updateById(one);
            return ResultObj.VERIFY_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.VERIFY_ERROR;
        }
    }
    /**
     * word文件材料补充
     * @param book
     * @return
     */
    @RequestMapping("wordSubmit")
    public ResultObj wordSubmit(Book book){
        User user = (User) getSession().getAttribute("user");
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.eq("userid",user.getId());
        bookQueryWrapper.eq("year",book.getYear());
        try {
            //用uerid+year找到学生对应学年记录
            Book one = bookService.getOne(bookQueryWrapper);
            //修改word文件路径
            one.setWordpath(book.getWordpath());
            one.setYpass("2");
            bookService.updateById(one);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.SUBMIT_ERROR;
        }
        return ResultObj.SUBMIT_SUCCESS;
    }
}

