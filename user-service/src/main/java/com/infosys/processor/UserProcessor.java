package com.infosys.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import com.infosys.model.User;

@Component
public class UserProcessor implements ItemProcessor<User, User>{

	@Override
	public User process(User item) throws Exception {
		return item;
	}

}
