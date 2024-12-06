package klu.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import klu.repository.StudentRepo;

@Service
public class StudentManager {
	@Autowired
	StudentRepo SR;
	public String getData(Long cPage, Long pSize)
	{
		List<String> slist = new ArrayList<String>();
		for(Student S : SR.findAll(PageRequest.of(cPage.intValue(), pSize.intValue())))
		{
			slist.add(toJSON(S));
		}
		return slist.toString();
	}
	
	//Convert JAVA Object to JSON
	public String toJSON(Object obj)
	{
		GsonBuilder GB = new GsonBuilder();
		Gson G = GB.create();
		return G.toJson(obj);
	}
		
	//Paging
	public List<String> getPaging(Long pSize)
	{
		Long totRec = SR.totRecords();
		//Page Count
		Long pages = (totRec / pSize) + ((totRec % pSize == 0) ? 0 : 1);
				
		Long startIndex = 1L;
		Long endIndex = pages;
		
		List<String> pagelist = new ArrayList<String>();
		for(Long i = startIndex; i<=endIndex; i++)
			pagelist.add(i.toString());
		
		return pagelist;
	}
}
