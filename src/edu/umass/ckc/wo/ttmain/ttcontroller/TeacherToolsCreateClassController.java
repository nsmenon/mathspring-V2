package edu.umass.ckc.wo.ttmain.ttcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.umass.ckc.wo.beans.ClassInfo;
import edu.umass.ckc.wo.ttmain.ttconfiguration.errorCodes.TTCustomException;
import edu.umass.ckc.wo.ttmain.ttmodel.CreateClassForm;
import edu.umass.ckc.wo.ttmain.ttservice.classservice.TTCreateClassAssistService;
import edu.umass.ckc.wo.ttmain.ttservice.loginservice.TTLoginService;

/**
 * Created by Neeraj on 3/26/2017.
 */


@Controller
public class TeacherToolsCreateClassController {

    @Autowired
    private TTLoginService loginService;

    @Autowired
    private TTCreateClassAssistService createClassAssistService;

    @RequestMapping(value = "/tt/ttCreateClass", method = RequestMethod.POST)
    public String createNewClass(@RequestParam("teacherId") String teacherId, @ModelAttribute("createClassForm") CreateClassForm classForm, ModelMap model) throws TTCustomException {

        //Basic Class Setup
        int newClassId = createClassAssistService.createNewClass(classForm, teacherId);
        //Set Default Pedagogy
        ClassInfo newClassInfo = createClassAssistService.addDefaultPedagogy(newClassId, classForm);
        //Add Student Roster and Finish setup

        if (!("".equals(classForm.getUserPrefix())) && classForm.getUserPrefix() != null
                && !("".equals(classForm.getPasswordToken())) && classForm.getPasswordToken() != null
                && classForm.getNoOfStudentAccountsForClass() > 0)
            createClassAssistService.createStudentRoster(newClassId, newClassInfo, classForm);

        createClassAssistService.changeDefaultProblemSets(model, newClassId);
        //Control Back to DashBoard with new Class visible
        loginService.populateClassInfoForTeacher(model, Integer.valueOf(teacherId));
        model.addAttribute("createClassForm", new CreateClassForm());
        return "teacherTools/classDetails";

    }


    @RequestMapping(value = "/tt/ttCloneClass", method = RequestMethod.POST)
    public String cloneExistingClass(@RequestParam("classId") String classId, @RequestParam("teacherId") String teacherId, @ModelAttribute("createClassForm") CreateClassForm classForm, ModelMap model) throws TTCustomException {
        //Clone Existing Class
        createClassAssistService.cloneExistingClass(Integer.valueOf(classId.trim()), classForm);
        return loginService.populateClassInfoForTeacher(model, Integer.valueOf(teacherId));

    }


    @RequestMapping(value = "/tt/ttResetSurvey", method = RequestMethod.POST)
    public String resetSurveySettings(@RequestParam("classId") String classId, @RequestParam("teacherId") String teacherId, @ModelAttribute("createClassForm") CreateClassForm classForm, ModelMap model) throws TTCustomException {
        //Clone Existing Class
         createClassAssistService.restSurveySettings(Integer.valueOf(classId.trim()), classForm);
        return loginService.populateClassInfoForTeacher(model, Integer.valueOf(teacherId));

    }


}
