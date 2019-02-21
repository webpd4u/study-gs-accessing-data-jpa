package hello;

import lombok.Value;

@Value
public class CustomerPhoneDto {
	private Long customer_id;
	private String first_name;

	private Long phone_id;
	private String number;
}
