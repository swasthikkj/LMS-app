package com.bridgelabz.lms.config;

import java.time.LocalDateTime;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.bridgelabz.lms.model.CandidateModel;

@Component
public class CandidateProcessor implements ItemProcessor<CandidateModel, CandidateModel> {

	@Override
	public CandidateModel process(CandidateModel candidate) throws Exception {
		
		candidate.setCreatedTimeStamp(LocalDateTime.now());
		candidate.setUpdatedTimeStamp(LocalDateTime.now());
		return candidate;
	}
}
