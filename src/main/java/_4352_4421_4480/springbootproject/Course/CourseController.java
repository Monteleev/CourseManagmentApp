package _4352_4421_4480.springbootproject.Course;

import _4352_4421_4480.springbootproject.student.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


//@RestController
@Controller

public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public String listCourses(Model model)
    {
        model.addAttribute("courses",courseService.getCourse());
        return "courses";
    }

    @GetMapping("/courses/syllabus/{id}")
    public String listCoursesSyllabus(@PathVariable Long id,Model model)
    {
        model.addAttribute("courses",courseService.getCourseById(id));
        return "course_syllabus";
    }

    @GetMapping("/courses/students/{id}")
    public String listStudents(@PathVariable Long id,Model model)
    {
        model.addAttribute("courses",courseService.getCourseById(id));
        return "course_students";
    }

    @GetMapping("/courses/new")
    public String createCourseForm(Model model)
    {
        Course course = new Course();
        model.addAttribute("course",course);
        return "create_course";
    }

    @PostMapping("/courses")
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.addNewCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/courses/edit/{courseId}")
    public String editCourseForm(@PathVariable Long courseId, Model model)
    {
        model.addAttribute("course",courseService.getCourseById(courseId));
        model.addAttribute("enrolledStudents", courseService.getCourseById(courseId).getEnrolledStudents());
        return "edit_course";
    }

    @GetMapping("/courses/{id}")
    public String deleteCourse(@PathVariable Long id)
    {
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }

    @PostMapping("/courses/{id}")
    public String updateCourse(@PathVariable Long id,
                                @ModelAttribute("course") Course course,
                                Model model) {

        // get student from database by id
        Course existingCourse = courseService.getCourseById(id);
        existingCourse.setId(id);
        existingCourse.setName(course.getName());
        existingCourse.setSyllabus(course.getSyllabus());
        existingCourse.setYear(course.getYear());
        existingCourse.setSemester(course.getSemester());


        courseService.updateCourse(existingCourse);
        return "redirect:/courses";
    }

    @RequestMapping("/courses/students/{id}")
    public @ResponseBody String studentIdSubmit(
            @PathVariable(value = "id") String courseId,
            @RequestParam(value = "studentId") Long studentId,
            Model model
    ) {
        model.addAttribute("studentId", studentId);
        System.out.println(studentId);
        System.out.println(courseId);
//        Student student = courseService.getStudentRepository().findById(studentId).get();
//        course.enrollStudent(student);
//        courseService.updateCourse(course);
        return "course_students";
    }


    //@RequestMapping(value = "/courses/{courseId}/students/{studentId}", method = RequestMethod.PUT)
    @PutMapping("/courses/{courseId}/students/{studentId}")
    String enrollStudentToCourse(@PathVariable("courseId") Long courseId,
                                 @PathVariable("studentId") Long studentId) {
        Course course = courseService.getCourseById(courseId);
        Student student = courseService.getStudentRepository().findById(studentId).get();
        course.enrollStudent(student);
        courseService.updateCourse(course);
        return "redirect:/courses_add_students";
    }
}