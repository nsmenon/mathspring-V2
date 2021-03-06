package edu.umass.ckc.wo.ttmain.ttservice.classservice;

import edu.umass.ckc.wo.beans.StudentDetails;
import edu.umass.ckc.wo.ttmain.ttconfiguration.errorCodes.TTCustomException;
import edu.umass.ckc.wo.ttmain.ttmodel.ClassStudents;
import edu.umass.ckc.wo.ttmain.ttmodel.EditStudentInfoForm;
import edu.umass.ckc.wo.ttmain.ttmodel.PerClusterObjectBean;
import edu.umass.ckc.wo.ttmain.ttmodel.PerProblemReportBean;
import org.w3c.dom.Document;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by nsmenon on 5/19/2017.
 */

public interface TTReportService {
    public String generateTeacherReport(String teacherId, String classId, String reportType, String lang) throws TTCustomException;

    public Map<String,List<String[]>> generateEmotionsReportForDownload(String teacherId, String classId) throws TTCustomException;

    public Map<String,PerClusterObjectBean> generatePerCommonCoreClusterReport(String classId);

    Map<String, List<Document>> generateEmotionMapValues(Map<String, String> studentIds) throws TTCustomException;

    public Map<String,Map<String, List<String>>> generateEfortMapValues(Map<String, String> studentIds, String classId);

    public List<ClassStudents> generateClassReportPerStudent(String teacherId, String classId);

    public Map<String,Object> generateClassReportPerStudentPerProblemSet(String teacherId, String classId) throws TTCustomException;

    public String getMasterProjectionsForCurrentTopic(String classId, String studentId, String topicID) throws TTCustomException;

    public String getCompleteMasteryProjectionForStudent(String classId, String studentId, String chartType) throws TTCustomException;

    public String generateReportForProblemsInCluster(String teacherId, String classId, String clusterId) throws TTCustomException;

    public Map<String, PerProblemReportBean> generatePerProblemReportForClass(String classId) throws TTCustomException;

    public List<EditStudentInfoForm> printStudentTags(String studentPassword, String classId) throws TTCustomException;
    
    public Map<String, Map<Integer,StudentDetails>> generateSurveyReport(String classId) throws TTCustomException;
}
