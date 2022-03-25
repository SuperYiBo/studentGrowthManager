package com.yeqifu.growth.controller;

import com.yeqifu.growth.service.*;
import com.yeqifu.sys.common.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class CountChekController {
    @Autowired
    private IWorkService workService;
    @Autowired
    private IVolunteerService volunteerService;
    @Autowired
    private IPatentService patentService;
    @Autowired
    private IPaperService paperService;
    @Autowired
    private IInnovateService iInnovateService;
    @Autowired
    private IHonorService honorService;
    @Autowired
    private ICompetitionService competitionService;
    @Autowired
    private IBookService bookService;
    @Autowired
    private ICertificateService certificateService;
    @PostMapping ("countCheck")
    public DataGridView countCheck(){
//       model.addAttribute("countBook", bookService.countBook());
        HashMap objectHashMap = new HashMap<String,Object>();
        Integer work = workService.countWork();
        Integer volunteer = volunteerService.countVolunteer();
        Integer patent = patentService.countPatent();
        Integer paper = paperService.countPaper();
        Integer innovate = iInnovateService.countInnovate();
        Integer honor = honorService.countHonor();
        Integer competition = competitionService.countCompetition();
        Integer certificate = certificateService.countCertificate();
        Integer book = bookService.countBook();
        Integer countAll=work+volunteer+patent+paper+innovate+honor+competition+certificate+book;
        objectHashMap.put("countBook",book);
        objectHashMap.put("countCertificate",certificate);
        objectHashMap.put("countCompetition",competition);
        objectHashMap.put("countHonor",honor);
        objectHashMap.put("countInnovate",innovate);
        objectHashMap.put("countPaper",paper);
        objectHashMap.put("countPatent",patent);
        objectHashMap.put("countVolunteer",volunteer);
        objectHashMap.put("countWork",work);
        objectHashMap.put("countAll",countAll);
        return new DataGridView(objectHashMap);
    }
}
