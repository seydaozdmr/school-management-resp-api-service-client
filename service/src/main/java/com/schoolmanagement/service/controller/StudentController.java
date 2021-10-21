package com.schoolmanagement.service.controller;

import com.schoolmanagement.service.dto.StudentDto;
import com.schoolmanagement.service.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * This is a shortcut, and though it may be useful in many situations,
 * it's not perfect. When more complex configuration is needed,
 * remove the annotation and extend WebMvcConfigurationSupport directly.
 */
@Api(description = "this is student controller")
@RestController
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    /**
     * List<StudentDto> olarak geri dönüş yapan /students get methodu
     */
    @ApiOperation(value = "Find all students and returns List of them")
    @GetMapping("/students")
    public List<StudentDto> findAll(){
        //System.out.println(language);
        return service.findAll();
    }

    @GetMapping("/students/transactions")
    public List<StudentDto> findAllStudentsWithTransactions(){
        return service.findAllStudentsWithTransactions();
    }

    /**
     * Using Spring, we usually have many ways to achieve the same goal,
     * including fine-tuning HTTP responses.
     *
     * In this short tutorial, we'll see how to set the body, status,
     * and headers of an HTTP response using ResponseEntity.
     *
     * ResponseEntity ile gönderdiğimiz cevabın body,status ve header kısımlarını
     * kendimiz oluşturabiliriz..
     * Yukarıdaki findAll methodunun aynısını responseEntity ile oluşturalım
     */
    @GetMapping("/getStudents")
    public ResponseEntity<List<StudentDto>> getStudents(){
        return new ResponseEntity<>(service.findAll(),HttpStatus.OK);
    }



    @ApiResponses(value = {
            @ApiResponse( code=200, message = "Found the book")})
    @GetMapping("/student/{id}")
    public StudentDto findById(@PathVariable("id") Integer id){
        return service.findStudentById(id);
    }

    /**
     * Furthermore, ResponseEntity provides two nested builder interfaces:
     * HeadersBuilder and its subinterface, BodyBuilder. Therefore, we can
     * access their capabilities through the static methods of ResponseEntity.
     *
     * The simplest case is a response with a body and HTTP 200 response code:
     */

    @GetMapping("/findStudent/{id}")
    public ResponseEntity<StudentDto> findStudent(@PathVariable("id") Integer id){
        //static method ile direkt olarak HttpStatus.OK response koduna sahip entity oluşturabiliyoruz.
        return ResponseEntity.ok(service.findStudentById(id));
    }


//    @PostMapping("/student/create")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Student saveStudent(@RequestBody Student student){
//        return service.saveStudent(student);
//    }

    /**
     * In addition, we can use the BodyBuilder status(HttpStatus status)
     * and the BodyBuilder status(int status) methods to set any HTTP status.
     *
     * Finally, with ResponseEntity<T> BodyBuilder.body(T body) we can set the HTTP response body:
     */

    @GetMapping("/age")
    public ResponseEntity<String> age(@RequestParam("yearOfBirth") int yearOfBirth) {
        if (isInFuture(yearOfBirth)) {
            return ResponseEntity.badRequest()
                    .header("Custom-Header", "foo")
                    .body("Year of birth cannot be in the future");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body("Your age is " + calculateAge(yearOfBirth));
    }

    private int calculateAge(int yearOfBirth) {
        return LocalDate.now().getYear()-yearOfBirth;
    }

    private boolean isInFuture(int yearOfBirth) {
        return LocalDate.now().getYear()<=yearOfBirth;
    }



    @PostMapping(value= "/student/create",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public StudentDto saveStudent(@RequestBody StudentDto student){
        return service.saveStudent(student);
    }

    @PutMapping(value = "/student/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDto updateStudent(@PathVariable("id") Integer id, @RequestBody StudentDto student){
        return service.updateStudent(id,student);
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable("id") Integer id){
        service.deleteStudent(id);
    }
}
