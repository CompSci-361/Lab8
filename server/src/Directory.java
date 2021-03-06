import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Directory {
	Gson gson = new Gson();
	ArrayList<Employee> employees = null;
	@SuppressWarnings("unchecked")
	public Directory() {		
		StringBuilder jsonBuilder = new StringBuilder();
		try {
			if (Files.exists(Paths.get("directory.json"))) {
				Files.readAllLines(Paths.get("directory.json")).forEach(line -> {
					jsonBuilder.append(line).append("\n");
				});
				
				employees = (ArrayList<Employee>)gson.fromJson(jsonBuilder.toString(), 
						new TypeToken<Collection<Employee>>() {
						}.getType());
			} else {
				employees = new ArrayList<Employee>();
			}
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			employees = new ArrayList<Employee>();
		}		
	}
	
	public ArrayList<Employee> getEmployeesFromDirectory() {
		return employees;
	}
	
	public String getDirectoryAsHtml() {
		String html = "<!DOCTYPE html>\n<html>\n<head>\n<title>Employee Directory</title>\n<link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\"/>\n</head><body><ul>";

		html += "<table>" + "<tr> <th>Title</th> <th>First Name</th> <th>Last Name</th> <th>Gender</th> <th>Department</th> <th>Phone</th></tr>";
		
		for(Employee e : getEmployeesFromDirectory()) {
		    html += "<tr><td>" + e.getTitle() + "</td>" + "<td>" + e.getFName() + "</td>" + "<td>" + e.getLName() + "</td>" + "<td>" + e.getGender() + "</td>" + "<td>" + e.getDepartment() + "</td>" + "<td>" + e.getPhone() + "</td>";
		}
		html += "</tr>" + "</table>";
		html += "</ul></body>\n</html>";
		
		writeHTML(html);
		return html;
		//TODO check this
	}
	
	public void addEmployeeFromJson(String json) {
		Employee employee = gson.fromJson(json, Employee.class);
		addEmployee(employee);
	}
	
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
	
	public void updateDirectory() {		
		String json = gson.toJson(employees);
		
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("directory.json"); //can be changed to .txt but .json is technically correct
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.append(json);
			
			bufferedWriter.flush();
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writeHTML(String html) {		
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("directory1.html"); //can be changed to .txt but .json is technically correct
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.append(html);
			
			bufferedWriter.flush();
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
