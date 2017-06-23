package gapp.web.controller;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import gapp.model.Additional;
import gapp.model.Application;
import gapp.model.Degree;
import gapp.model.Department;
import gapp.model.Doc;
import gapp.model.Program;
import gapp.model.Status;
import gapp.model.User;
import gapp.model.dao.UserDao;
import gapp.web.validator.Validator;
import com.google.gson.Gson;

@Controller
public class HomeController {

	@Autowired
	private Validator Validator;

	@Autowired
	private UserDao userDao;

	@RequestMapping({ "/index.html", "/home.html" })
	public String index() {
		return "home";
	}

	@RequestMapping(value = "/register.html", method = RequestMethod.GET)
	public String signup(ModelMap models) {
		models.put("user", new User());
		return "register";
	}

	@RequestMapping(value = "/register.html", method = RequestMethod.POST)
	public String signup(@ModelAttribute User user, BindingResult result, HttpServletRequest request) {
		Validator.validate(user, result);
		if (result.hasErrors())
			return "register";
		user = userDao.register(user);
		request.getSession().setAttribute("user", user);
		return "redirect:login.html";
	}

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String login(ModelMap models, SessionStatus status) {
		status.setComplete();
		models.put("user", new User());
		return "login";
	}

	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	public String login(ModelMap models, @ModelAttribute User user, BindingResult result, HttpServletRequest request,
			@RequestParam String email, @RequestParam String password) {

		for (User u : userDao.getUsers()) {
			if (u.getEmail().equalsIgnoreCase(email) && u.getPassword().equalsIgnoreCase(password)) {

				request.getSession().setAttribute("user", u);
				return "redirect:admin.html";

			}

		}
		return "login";
	}

	@RequestMapping(value = "/admin.html", method = RequestMethod.GET)
	public String admin(ModelMap models, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user.isAdmin()) {
			models.put("departments", userDao.getDepartments());
			return "admin";
		} else if (user.isStaff()) {
			models.put("departments", userDao.getDepartments());
			return "redirect:staff.html";
		} else {
			models.put("departments", userDao.getDepartments());
			return "redirect:applicant.html";
		}
	}

	@RequestMapping(value = "/view.html", method = RequestMethod.GET)
	public String department_view(ModelMap models, HttpServletRequest request, @RequestParam Long id) {
		User user = (User) request.getSession().getAttribute("user");
		models.put("department", userDao.getDepartment(id));
		return "view";
	}

	@RequestMapping(value = "/add.html", method = RequestMethod.GET)
	public String add(ModelMap models, HttpServletRequest request, @RequestParam(required = false) Long Id) {
		// User user = (User) request.getSession().getAttribute("user");
		if (Id == null) {
			models.put("department", new Department());
			return "add";
		} else {

			models.put("department", userDao.getDepartment(Id));
			return "add";
		}
	}

	@RequestMapping(value = "/add.html", method = RequestMethod.POST)
	public String add(ModelMap models, HttpServletRequest request, @ModelAttribute Department department,
			@RequestParam(required = false) String done, @RequestParam(required = false) String add,
			@RequestParam(required = false) String program, @RequestParam(required = false) String info) {
		// User user = (User) request.getSession().getAttribute("user");
		if (done.equals("add1")) {

			Program p = new Program();
			p.setName(program);
			p.setDepartment(department);
			System.out.println(p.getName());
			System.out.println(department.getPrograms().size());
			department.getPrograms().add(p);
			department = userDao.saveDepartment(department);

			return "redirect:add.html?Id=" + department.getId();
		} else if (done.equals("add2")) {

			Additional a = new Additional();
			a.setName(info);
			a.setDepartment(department);
			System.out.println(a.getName());

			// System.out.println(department.getPrograms().size());
			department.getAdditional().add(a);
			department = userDao.saveDepartment(department);

			return "redirect:add.html?Id=" + department.getId();
		} else {
			return "redirect:admin.html";
		}

		// department = userDao.saveDepartment(department);

	}

	@RequestMapping(value = "/add2.html", method = RequestMethod.GET)
	public String add2(ModelMap models, HttpServletRequest request, @RequestParam(required = false) Long Id) {
		// User user = (User) request.getSession().getAttribute("user");

		models.put("department", userDao.getDepartment(Id));
		return "add2";

	}

	@RequestMapping(value = "/add2.html", method = RequestMethod.POST)
	public String add2(ModelMap models, HttpServletRequest request, @ModelAttribute Department department,
			@RequestParam(required = false) String done, @RequestParam(required = false) String add,
			@RequestParam(required = false) String program, @RequestParam(required = false) String info) {
		// User user = (User) request.getSession().getAttribute("user");
		if (done.equals("add2")) {
			System.out.println("hi");
			Additional a = new Additional();
			a.setName(info);
			a.setDepartment(department);
			System.out.println(a.getName());

			// System.out.println(department.getPrograms().size());
			department.getAdditional().add(a);
			department = userDao.saveDepartment(department);

			return "redirect:add2.html?Id=" + department.getId();
		} else {

			Additional a = new Additional();
			a.setName(info);
			a.setDepartment(department);
			// p = userDao.saveProgram(p);
			// department.getAdditional().add(a);
			department.getAdditional().add(a);
			department = userDao.saveDepartment(department);

			return "redirect:login.html";
		}
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public String edit(ModelMap models, HttpServletRequest request, @RequestParam(required = false) Long id) {
		// User user = (User) request.getSession().getAttribute("user");

		request.getSession().setAttribute("department", userDao.getDepartment(id));
		models.put("department", userDao.getDepartment(id));
		return "edit";

	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	public String edit(ModelMap models, HttpServletRequest request, @ModelAttribute Department department,
			@RequestParam(required = false) String done, @RequestParam(required = false) String add,
			@RequestParam(required = false) String program, @RequestParam(required = false) String info) {
		// User user = (User) request.getSession().getAttribute("user");
		if (done.equals("add1")) {

			Program p = new Program();
			p.setName(program);
			p.setDepartment(department);
			System.out.println(p.getName());
			System.out.println(department.getPrograms().size());
			department.getPrograms().add(p);
			department = userDao.saveDepartment(department);
			System.out.println(department.getId());

			return "redirect:edit.html?id=" + department.getId();
		} else if (done.equals("add2")) {

			Additional a = new Additional();
			a.setName(info);
			a.setDepartment(department);
			System.out.println(a.getName());

			// System.out.println(department.getPrograms().size());
			department.getAdditional().add(a);
			department = userDao.saveDepartment(department);

			return "redirect:edit.html?id=" + department.getId();
		} else {
			return "redirect:admin.html";
		}

		// department = userDao.saveDepartment(department);

	}

	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public String delete(ModelMap models, HttpServletRequest request, @RequestParam Long id) {
		// User user = (User) request.getSession().getAttribute("user");
		Department department = (Department) request.getSession().getAttribute("department");
		userDao.delete(userDao.getProgram(id));
		return "redirect:edit.html?id=" + department.getId();
	}

	@RequestMapping(value = "/deleteA.html", method = RequestMethod.GET)
	public String deleteA(ModelMap models, HttpServletRequest request, @RequestParam Long id) {
		// User user = (User) request.getSession().getAttribute("user");
		Department department = (Department) request.getSession().getAttribute("department");
		userDao.deleteA(userDao.getAdditional(id));
		return "redirect:edit.html?id=" + department.getId();
	}

	@RequestMapping(value = "/staff.html", method = RequestMethod.GET)
	public String staff(ModelMap models, HttpServletRequest request, @RequestParam(required = false) Long Id) {

		return "staff";

	}

	@RequestMapping(value = "/logout.html", method = RequestMethod.GET)
	public String logout(ModelMap models, HttpServletRequest request, @RequestParam(required = false) Long Id,
			SessionStatus status) {
		status.setComplete();
		User user = (User) request.getSession().getAttribute("user");
		return "redirect:login.html";

	}

	@RequestMapping(value = "/applicant.html", method = RequestMethod.GET)
	public String applicant(ModelMap models, HttpServletRequest request, @RequestParam(required = false) Long Id) {

		User u = (User) request.getSession().getAttribute("user");
		User user = userDao.getUser(u.getId());
		models.put("user", user);
		models.put("applications", user.getApplications());
		System.out.println(user.getApplications().size());

		return "applicant";

	}

	@RequestMapping(value = "/addApp.html", method = RequestMethod.GET)
	public String addApp(ModelMap models, HttpServletRequest request, @RequestParam(required = false) Long Id,
			@RequestParam(required = false) Long department, HttpServletResponse response) throws IOException {
		String json = null;
		List<String> list = new ArrayList<String>();
		Application a = new Application();

		User user = (User) request.getSession().getAttribute("user");
		System.out.println(user.getId() + "ille");
		a.setApplicant(userDao.getUser(user.getId()));
		models.put("user", user);
		System.out.println(department);
		models.put("departments", userDao.getDepartments());
		// models.put("programs",)
		// int id = Integer.parseInt(department);
		// System.out.println(id);
		if (department != null) {
			models.put("programs", userDao.getDepartment(department).getPrograms());

			System.out.println(userDao.getDepartment(department).getPrograms().size());
			for (Program p : userDao.getDepartment(department).getPrograms()) {
				list.add(p.getName());
			}
			json = new Gson().toJson(list);
			response.setContentType("application/json");
			response.getWriter().write(json);
			models.put("application", a);
			return null;
		}

		// System.out.println(d.getName());

		return "addApp";

	}

	@RequestMapping(value = "/addApp.html", method = RequestMethod.POST)
	public String addApp(@ModelAttribute Application application, @RequestParam String term, ModelMap models,
			HttpServletRequest request, @RequestParam(required = false) Long Id,
			@RequestParam(required = false) Long dep, HttpServletResponse response,
			@RequestParam(required = false) String prog) throws IOException {
		System.out.println("check 1");
		User user = (User) request.getSession().getAttribute("user");
		Status s = new Status();
		s.setCondition(true);
		System.out.println(prog);
		System.out.println(userDao.getProgramByName(prog).getId());
		application.setProgram(userDao.getProgramByName(prog));
		application.setDepartment(userDao.getDepartment(dep));
		application.setStatus(s);
		application.setTerm(term);
		application.setSubmit_on(new Date().toString());
		application.setApplicant(userDao.getUser(user.getId()));
		request.getSession().setAttribute("user", user);
		application = userDao.saveApplication(application);
		request.getSession().setAttribute("user", user);
		System.out.println(user.getApplications().size() + "boom");
		request.getSession().setAttribute("a", application);
		return "redirect:addDegree.html?appId=" + application.getId();

	}

	@RequestMapping(value = "/addDegree.html", method = RequestMethod.GET)
	public String addDegree(ModelMap models, HttpServletRequest request, @RequestParam(required = false) Long appId,
			SessionStatus status) {

		Application a = (Application) request.getSession().getAttribute("a");
		System.out.println(appId + "got");
		Degree degree = new Degree();
		models.put("degree", degree);
		models.put("application", a);
		return "addDegree";

	}

	@RequestMapping(value = "/addDegree.html", method = RequestMethod.POST)
	public String addDegree(ModelMap models, @RequestParam(required = false) String name,
			@RequestParam(required = false) String school, @RequestParam(required = false) String year,
			@ModelAttribute Application application, @ModelAttribute Degree degree, HttpServletRequest request,
			@RequestParam(required = false) Long Id, SessionStatus status,
			@RequestParam(required = false) String finish) {

		if (finish == null) {
			Application a = (Application) request.getSession().getAttribute("a");
			degree.setName(name);
			degree.setSchool(school);
			int y = Integer.parseInt(year);
			degree.setYear(y);
			degree.setApplication(a);
			System.out.println(a.getId() + "check2");
			a.getDegrees().add(degree);
			a = userDao.saveApplication(a);
			request.getSession().setAttribute("a", a);

			System.out.println(application.getId() + "hi");
			return "redirect:addDegree.html?appId=" + a.getId();
		} else {
			Application a = (Application) request.getSession().getAttribute("a");
			degree.setName(name);
			degree.setSchool(school);
			int y = Integer.parseInt(year);
			degree.setYear(y);
			degree.setApplication(a);
			a.getDegrees().add(degree);
			a = userDao.saveApplication(a);
			request.getSession().setAttribute("a", a);

			// ModelAndView view=new ModelAndView("redirect:applicant.html");
			return "redirect:academic.html?appId=" + a.getId();
		}

	}

	@RequestMapping(value = "/academic.html", method = RequestMethod.GET)
	public String academic(ModelMap models, HttpServletRequest request, @RequestParam(required = false) Long appId,
			SessionStatus status) {

		Application a = (Application) request.getSession().getAttribute("a");

		return "academic";

	}

	@RequestMapping(value = "/academic.html", method = RequestMethod.POST)
	public String academic(ModelMap models, SessionStatus status, @RequestParam(required = false) String gpa,
			@RequestParam(required = false) String gre, @RequestParam(required = false) String ielts,
			@RequestParam MultipartFile transcript, HttpServletRequest request,
			@RequestParam(required = false) Long appId, @RequestParam(required = false) String save, SessionStatus s)
					throws IOException {
		User user = (User) request.getSession().getAttribute("user");
		Application a = (Application) request.getSession().getAttribute("a");
		byte[] byteArr = transcript.getBytes();
		InputStream inputStream = new ByteArrayInputStream(byteArr);
		System.out.println(inputStream.toString());
		double value1 = Double.parseDouble(gpa);
		double value2 = Double.parseDouble(gre);
		double value3 = Double.parseDouble(ielts);
		a.setGpa(value1);
		a.setGre(value2);
		a.setTofel(value3);
		Doc f1 = new Doc();
		f1.setDate(new Date());
		f1.setName(transcript.getOriginalFilename());

		f1.setOwner(user);
		f1 = userDao.saveDoc(f1);
		a.setTranscript(f1);

		transcript.transferTo(new java.io.File(userDao.getFileDirectory(), f1.getId().toString()));
		a.setApplicant(userDao.getUser(user.getId()));
		if (save == null) {
			System.out.println("save null");
			a.getStatus().setName("Submitted");
			a = userDao.saveApplication(a);
		} else {
			a = userDao.saveApplication(a);
		}
		s.setComplete();
		request.getSession().setAttribute("user", user);
		System.out.println(user.getApplications().size() + "yes");

		return "redirect:applicant.html";

	}

	@RequestMapping(value = "/EditApp.html", method = RequestMethod.GET)
	public String EditApp(ModelMap models, HttpServletRequest request, @RequestParam(required = false) Long Editid,
			@RequestParam(required = false) Long Id, @RequestParam(required = false) Long department,
			HttpServletResponse response) throws IOException {
		String json = null;
		List<String> list = new ArrayList<String>();
		// Application a = new Application();

		User user = (User) request.getSession().getAttribute("user");
		models.put("application", userDao.getApplication(Editid));
		request.getSession().setAttribute("a1", userDao.getApplication(Editid));
		// a.setApplicant(userDao.getUser(user.getId()));
		models.put("user", user);

		return "EditApp";

	}

	@RequestMapping(value = "/EditApp.html", method = RequestMethod.POST)
	public String EditApp(@RequestParam String term, ModelMap models, HttpServletRequest request,
			@RequestParam(required = false) Long Id, @RequestParam(required = false) String depa,
			HttpServletResponse response, @RequestParam(required = false) Long prog) throws IOException {
		Application a = (Application) request.getSession().getAttribute("a1");
		// System.out.println(a.getId()+"jo");
		Application application = userDao.getApplication(a.getId());
		User user = (User) request.getSession().getAttribute("user");
		Status s = new Status();
		s.setCondition(true);
		application.setProgram(userDao.getProgram(prog));
		application.setDepartment(userDao.getDepartmentByName(depa));
		application.setStatus(s);
		application.setTerm(term);
		application.setSubmit_on(new Date().toString());
		application.setApplicant(userDao.getUser(user.getId()));

		application = userDao.saveApplication(application);
		// System.out.println(application.getId());
		request.getSession().setAttribute("a", application);
		return "redirect:EditDegree.html?appId=" + application.getId();

	}

	@RequestMapping(value = "/deleteDegree.html", method = RequestMethod.GET)
	public String deleteDegree(ModelMap models, HttpServletRequest request, @RequestParam Long id) {
		Application a = (Application) request.getSession().getAttribute("a");
		// User user = (User) request.getSession().getAttribute("user");
		// Department department = (Department)
		// request.getSession().getAttribute("department");
		userDao.deleteDegree(userDao.getDegree(id));
		request.getSession().setAttribute("a", a);
		return "redirect:EditDegree.html";
	}

	@RequestMapping(value = "/EditDegree.html", method = RequestMethod.GET)
	public String EditDegree(ModelMap models, HttpServletRequest request, @RequestParam(required = false) Long appId,
			SessionStatus status) {

		Application a1 = (Application) request.getSession().getAttribute("a");
		Application a = userDao.getApplication(a1.getId());
		System.out.println(appId + "got");
		Degree degree = new Degree();
		models.put("degree", degree);
		models.put("application", a);
		return "EditDegree";

	}

	@RequestMapping(value = "/EditDegree.html", method = RequestMethod.POST)
	public String EditDegree(ModelMap models, @RequestParam(required = false) String name,
			@RequestParam(required = false) String school, @RequestParam(required = false) String year,
			@ModelAttribute Application application, @ModelAttribute Degree degree, HttpServletRequest request,
			@RequestParam(required = false) Long Id, SessionStatus status,
			@RequestParam(required = false) String finish) {

		if (finish == null) {
			Application a1 = (Application) request.getSession().getAttribute("a");
			Application a = userDao.getApplication(a1.getId());
			System.out.println(a.getDegrees().size());
			degree.setName(name);
			degree.setSchool(school);
			int y = Integer.parseInt(year);
			degree.setYear(y);
			degree.setApplication(a);
			System.out.println(a.getId() + "check2");
			a.getDegrees().add(degree);
			a = userDao.saveApplication(a);
			request.getSession().setAttribute("a", a);

			// System.out.println(application.getId()+"hi");
			return "redirect:EditDegree.html?appId=" + a.getId();
		} else {
			System.out.println("hola");
			Application a1 = (Application) request.getSession().getAttribute("a");
			Application a = userDao.getApplication(a1.getId());
			degree.setName(name);
			degree.setSchool(school);
			int y = Integer.parseInt(year);
			degree.setYear(y);
			degree.setApplication(a);
			a.getDegrees().add(degree);
			a = userDao.saveApplication(a);
			request.getSession().setAttribute("a", a);

			// ModelAndView view=new ModelAndView("redirect:applicant.html");
			return "redirect:EditAcademic.html?appId=" + a.getId();
		}

	}

	@RequestMapping(value = "/EditAcademic.html", method = RequestMethod.GET)
	public String Eacademic(ModelMap models, HttpServletRequest request, @RequestParam(required = false) Long appId,
			SessionStatus status) {

		Application a1 = (Application) request.getSession().getAttribute("a");
		Application a = userDao.getApplication(a1.getId());
		models.put("a", a);

		return "EditAcademic";

	}

	@RequestMapping(value = "/EditAcademic.html", method = RequestMethod.POST)
	public String Eacademic(ModelMap models, @RequestParam(required = false) String save,
			@RequestParam(required = false) String gpa, @RequestParam(required = false) String gre,
			@RequestParam(required = false) String ielts, @RequestParam MultipartFile transcript,
			HttpServletRequest request, @RequestParam(required = false) Long appId, SessionStatus status)
					throws IOException {
		User user = (User) request.getSession().getAttribute("user");
		Application a = (Application) request.getSession().getAttribute("a");
		byte[] byteArr = transcript.getBytes();
		InputStream inputStream = new ByteArrayInputStream(byteArr);
		System.out.println(inputStream.toString());
		double value1 = Double.parseDouble(gpa);
		double value2 = Double.parseDouble(gre);
		double value3 = Double.parseDouble(ielts);
		a.setGpa(value1);
		a.setGre(value2);
		a.setTofel(value3);
		Doc f1 = new Doc();
		f1.setDate(new Date());
		f1.setName(transcript.getOriginalFilename());

		f1.setOwner(user);
		f1 = userDao.saveDoc(f1);
		a.setTranscript(f1);
		// a = userDao.saveApplication(a);
		transcript.transferTo(new java.io.File(userDao.getFileDirectory(), f1.getId().toString()));
		if (save == null) {

			a.getStatus().setName("Submitted");
			a = userDao.saveApplication(a);
		} else {
			a = userDao.saveApplication(a);
		}
		return "redirect:applicant.html";

	}

	@RequestMapping(value = "/view1.html", method = RequestMethod.GET)
	public String view1(ModelMap models, HttpServletRequest request, @RequestParam(required = false) Long id,
			SessionStatus status) {

		Application a1 = (Application) request.getSession().getAttribute("a");
		// Application a = userDao.getApplication(a1.getId());
		models.put("application", userDao.getApplication(id));

		return "view1";

	}

	@RequestMapping("/download.html")
	public String download(HttpSession session, @RequestParam Long id, HttpServletResponse response)
			throws IOException {

		Doc d = userDao.getDoc(id);
		response.setHeader("Content-Disposition", "inline; filename=" + d.getName());
		FileInputStream in = new FileInputStream(new java.io.File(userDao.getFileDirectory(), id.toString()));
		OutputStream out = response.getOutputStream();

		byte buffer[] = new byte[2048];
		int bytesread;
		while ((bytesread = in.read(buffer)) > 0)
			out.write(buffer, 0, bytesread);
		in.close();

		return null;

	}

}