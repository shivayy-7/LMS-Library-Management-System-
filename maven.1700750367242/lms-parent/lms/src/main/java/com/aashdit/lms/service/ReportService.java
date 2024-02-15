package com.aashdit.lms.service;

import java.util.List;

import com.aashdit.lms.dto.CatagoriesSubCatagoresReport;
import com.aashdit.lms.dto.CategoriesFillterDto;

public interface ReportService {

	List<CatagoriesSubCatagoresReport> categorieswiseData(CategoriesFillterDto categoriesFillterDto);

}
