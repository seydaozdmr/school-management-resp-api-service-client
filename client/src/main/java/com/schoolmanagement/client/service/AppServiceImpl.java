package com.schoolmanagement.client.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schoolmanagement.client.dto.Student;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppServiceImpl implements AppService {
    private final String GET_STUDENTS="http://localhost:8080/getStudents";
    private final String GET_STUDENTS_LIST="http://localhost:8080/students";
    private final String SAVE_STUDENT="http://localhost:8080/student/create";
    private final String GET_STUDENT="http://localhost:8080/student/";
    private final String UPDATE_STUDENT="http://localhost:8080/student/";

    private RestTemplate restTemplate;
    //Autowired
    public AppServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * ResponseEntity<List> olarak response alıyorum ve .getBody() methodu ile List<Student>
     * listesini oluşturuyorum ve geri dönüyorum.
     * @return
     */
    @Override
    public List<Student> getStudents() {
        ResponseEntity<List> data = restTemplate.getForEntity(GET_STUDENTS, List.class);
        List<Student> students=data.getBody();
        return students;
    }

    /**
     * List olarak response alıyorum ve bunu ObjectMapper ile List<Student> tipine çeviriyorum.
     * @return List<Student>
     */
    @Override
    public List<Student> getAllStudents()  {
        List response= restTemplate.getForObject(GET_STUDENTS_LIST,List.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Student> students=mapper.convertValue(response.toArray(), new TypeReference<List<Student>>() {
        });
        for(Student s:students){
            System.out.println(s.getName());
        }
        return students;
    }

    @Override
    public List<Student> getAll() {
        Student[] studentArray=restTemplate.getForObject(GET_STUDENTS_LIST,Student[].class);
        return Arrays.asList(studentArray);
    }



    @Override
    public List<Student> getAllWithHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("X-COM-PERSIST","NO");
        headers.set("X-COM-LOCATION","US");

        HttpEntity<String> entity=new HttpEntity<>(headers);
        ResponseEntity<Student[]> responseEntity=restTemplate.exchange(GET_STUDENTS,HttpMethod.GET,entity,Student[].class);

        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public List<Student> getStudentListAsResponseEntity() {
        ResponseEntity<List> responseList=restTemplate.getForEntity(GET_STUDENTS_LIST,List.class);
        List<Student> studentList=responseList.getBody();
        return studentList;
    }

    @Override
    public List<Student> getStudentListAsObject() {
        List response= restTemplate.getForObject(GET_STUDENTS,List.class);
        ObjectMapper mapper = new ObjectMapper();
        List<Student> students=mapper.convertValue(response.toArray(), new TypeReference<List<Student>>() {
        });
        for(Student s:students){
            System.out.println(s.getName());
        }
        return students;
    }

    @Override
    public Student saveStudent(Student student) {
        ResponseEntity<Student> responseEntity=restTemplate.postForEntity(SAVE_STUDENT,student,Student.class);
        Student student1=responseEntity.getBody();
        return student1;
    }

    @Override
    public Student getStudent(Integer id) {
        ResponseEntity<Student> studentResponseEntity=restTemplate.getForEntity(GET_STUDENT+id,Student.class);
        return studentResponseEntity.getBody();
    }

    @Override
    public Student findStudent(Integer id){
        Map<String,String> params=new HashMap<>();
        /**
         * Yukarıdaki GET_STUDENT uri'ne id ekleyerek restTemplate'e gönderiyorum.
         */
        UriComponentsBuilder uriComponentsBuilder=UriComponentsBuilder.fromUriString(GET_STUDENT)
                .path(id.toString());

        params.put("id","1");
        Student student=restTemplate.getForObject(uriComponentsBuilder.toUriString(),Student.class,params);

        return student;
    }

    @Override
    public Student updateStudent(Integer id, Student student) {
        ResponseEntity<Student> responseEntity=restTemplate.exchange(UPDATE_STUDENT+id, HttpMethod.PUT,new HttpEntity<>(student),Student.class);
        return responseEntity.getBody();
    }

    @Override
    public Student updateStudentState(Integer id,Student student){
        restTemplate.put(UPDATE_STUDENT+id,student,Student.class);
        return student;
    }


}
