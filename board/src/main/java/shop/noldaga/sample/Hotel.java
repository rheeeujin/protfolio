package shop.noldaga.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Component
public class Hotel {
	@Autowired
	private Chef chef;

	public Hotel(Chef chef) {
		this.chef = chef;
	}
	
}
