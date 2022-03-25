package com.yeqifu.sys.controller;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yeqifu.sys.common.AppFileUtils;
import com.yeqifu.sys.common.Constast;
import com.yeqifu.sys.common.ResultObj;
import com.yeqifu.sys.entity.Grade;
import com.yeqifu.sys.entity.User;
import com.yeqifu.sys.service.IGradeService;
import com.yeqifu.sys.service.IRoleService;
import com.yeqifu.sys.service.IUserService;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IGradeService gradeService;

    @Autowired
    private IUserService userService;
    /**
     * 文件上传,选择图片就直接上传，无需点击
     *
     * @param mf
     * @return
     */
    @RequestMapping("uploadFile")
    public Map<String, Object> uploadFile(MultipartFile mf) {
        //1.得到文件名
        String oldName = mf.getOriginalFilename();
        //2.根据旧的文件名生成新的文件名
        String newName = AppFileUtils.createNewFileName(oldName);
        //3.得到当前日期的字符串
        String dirName = DateUtil.format(new Date(), "yyyy-MM-dd");
        //4.构造文件夹
        File dirFile = new File(AppFileUtils.UPLOAD_PATH, dirName);
        //5.判断当前文件夹是否存在
        if (!dirFile.exists()) {
            //如果不存在则创建新文件夹
            dirFile.mkdirs();
        }
        //6.构造文件对象
        File file = new File(dirFile, newName + "_temp");
        //7.把mf里面的图片信息写入file
        try {
            mf.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("path", dirName + "/" + newName + "_temp");
        return map;
    }

    /**
     * 图片下载
     */
    @RequestMapping("showImageByPath")
    public ResponseEntity<Object> showImageByPath(String path) {
        return AppFileUtils.createResponseEntity(path);
    }

    /**
     * word文件上传，点击上传才会上传
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "uploadSource", method = RequestMethod.POST)
    @ResponseBody
    public String uploadSource(@RequestParam("file") MultipartFile file) {
        System.out.println(file);
        String pathString = null;
        if (file != null) {
            //获取上传的文件名称
            String filename = file.getOriginalFilename();
            //文件上传时，chrome与IE/Edge对于originalFilename处理方式不同
            //chrome会获取到该文件的直接文件名称，IE/Edge会获取到文件上传时完整路径/文件名
            //Check for Unix-style path
            int unixSep = filename.lastIndexOf('/');
            //Check for Windows-style path
            int winSep = filename.lastIndexOf('\\');
            //cut off at latest possible point
            int pos = (winSep > unixSep ? winSep : unixSep);
            System.out.println(pos);
            if (pos != -1)
                filename = filename.substring(pos + 1);
            pathString = AppFileUtils.UPLOAD_PATH + "/docx/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + filename;//上传到本地
        }
        try {
            File files = new File(pathString);//在内存中创建File文件映射对象
            //打印查看上传路径
            System.out.println(pathString);
            if (!files.getParentFile().exists()) {//判断映射文件的父文件是否真实存在
                files.getParentFile().mkdirs();//创建所有父文件夹
            }
            file.transferTo(files);//采用file.transferTo 来保存上传的文件
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "{\"code\":0, \"msg\":\"success\", \"fileUrl\":\"" + pathString + "\"}";
    }



    /**
     * 下载导入模板
     */
    @RequestMapping("downloadExcel")
    public void downloadTemplate (HttpServletResponse response , HttpServletRequest request) throws IOException {
        try {
            ClassPathResource resource = new ClassPathResource("excel/student.xlsx");
            InputStream is = resource.getInputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-disposition", "attachment;filename=student.xlsx");
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        }catch (Exception e){
            e.printStackTrace ();
        }


    }

    /**
     * 上传excel并解析
     */
    @RequestMapping("importStudents")
    public ResultObj uploadExcel (MultipartFile file ) {
        try {
            Workbook workbook = new XSSFWorkbook ( file.getInputStream () );
            //获取第一个sheet页码
            Sheet sheet = workbook.getSheetAt ( 0 );
            List<User> users = new ArrayList<>(  );
            //循环行数row
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum (); rowNum++) {
                Row row = sheet.getRow ( rowNum );
                if ( row == null ) {
                    continue;
                }
                Object[] obj = new Object[row.getLastCellNum ()];
                //循环每行row所有数据cell
                for (int cellNum=0;cellNum<row.getLastCellNum ();cellNum++){
                    //获取每格数据内容,存入数组
                    Cell cell = row.getCell ( cellNum );
                    if ( cell == null ) {
                        continue;
                    }
                    Object value = getValue ( cell );
                    obj[cellNum]=value;
                }
                User user = new User ();
                user.setName ( (String)obj[0] );
                if(((String)obj[1]).equals("男")){
                    obj[1]=1;
                }else{
                    obj[1]=0;
                }
                user.setSex ( (Integer) obj[1] );
                System.out.println(obj[2]+"======================================================");
                user.setLoginname ( obj[2].toString() );
                QueryWrapper<Grade> objectQueryWrapper = new QueryWrapper<>();
                objectQueryWrapper.eq("name", (String)obj[3]);
                try {
                    obj[3] = gradeService.getOne(objectQueryWrapper).getId();
                } catch (Exception e) {
                    e.printStackTrace();
                    obj[3] = 1;
                }
                user.setGradeid((Integer)obj[3]);
                user.setAddress((String)obj[4]);
                user.setEmail ( (String ) obj[5] );
                //设置类型
                user.setType(Constast.USER_TYPE_NORMAL);
                //设置盐
                String salt = IdUtil.simpleUUID().toUpperCase();
                user.setSalt(salt);
                //设置默认密码
                user.setPwd(new Md5Hash(Constast.USER_DEFAULT_PWD,salt,2).toString());
                //设置用户默认头像
                user.setImgpath(Constast.DEFAULT_IMG_USER);
                user.setCreatedate(new Date());
                user.setAvailable(1);
                user.setRemark("无");
                users.add(user);
            }
            this.userService.saveBatch ( users );
            for (User user:users){
                //给用户添加默认添加上学生角色
                Integer userId = userService.getIdByLoginName(user.getLoginname());
                roleService.insertUserRole(userId,Constast.STUDENT_ROLE);
            }
            return ResultObj.IMPORT_SUCCESS;
        } catch (IOException e) {
            e.printStackTrace ();
            return ResultObj.IMPORT_ERROR;
        }
    }


    private Object getValue ( Cell cell ) {
        Object value = null;
        switch (cell.getCellType ()) {
            case STRING:
                value = cell.getRichStringCellValue ().getString ();
                break;
            case NUMERIC:
                if ( org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted ( cell ) ) {
                    value = cell.getDateCellValue ();
                } else {
                    value = cell.getNumericCellValue ();
                }
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue ();
                break;
            case FORMULA:
                value = cell.getCellFormula ();
                break;
            case _NONE:
                value = null;
                break;
            default:
                break;
        }
        return value;
    }




}
