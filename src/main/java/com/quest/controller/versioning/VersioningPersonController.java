package com.quest.controller.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	
	/*URI Versioning

	V1: http://localhost:8080/v1/person

	@GetMapping("/v1/person")

	V2: http://localhost:8080/v2/person

	@GetMapping("/v2/person")
	*/
	
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionPerson() {
		return new PersonV1("Vandana Jogi");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionPerson() {
		return new PersonV2(new Name("Vandana", "Jogi"));
	}
	
	
	/*
	 * Request Param Versioning
	 * 
	 * V1: http://localhost:8080/person?version=1
	 * 
	 * @GetMapping(path = "/person", params = "version=1")
	 * 
	 * V2: http://localhost:8080/person?version=2
	 * 
	 * @GetMapping(path = "/person", params = "version=2")
	 */
	//By using request params    http://localhost:8080/person?version=1
	@GetMapping(path="/person",params="version=1")
	public PersonV1 getfirstVersionPersonByReqParameter() {
		return new PersonV1("Vandana Jogi");
	}
	
	@GetMapping(path="/person",params="version=2")
	public PersonV2 getSecondVersionPersonByRequestParam() {
		return new PersonV2(new Name("Vandana", "Jogi"));
	}
	
	/*
	 * Header Versioning
	 * 
	 * V1: http://localhost:8080/person/header
	 * 
	 * HEADER - X-API-VERSION:1
	 * 
	 * @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	 * 
	 * V2: http://localhost:8080/person/header
	 * 
	 * HEADER - X-API-VERSION:2
	 * 
	 * @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
	 */
	
	
	@GetMapping(path="/person/header",params="X-API-VERSION=1")
	public PersonV1 getfirstVersionPersonByReqParaHeader() {
		return new PersonV1("Vandana Jogi");
	}
	
	@GetMapping(path="/person/header",params="X-API-VERSION=2")
	public PersonV2 getSecondVersionPersonByReqParaHeader() {
		return new PersonV2(new Name("Vandana", "Jogi"));
	}
	
	
	/*
	 * Content Negotiation Versioning
	 * 
	 * V1: http://localhost:8080/person/accept
	 * 
	 * HEADER - Accept:application/vnd.company.app-v1+json
	 * 
	 * @GetMapping(path = "/person/accept", produces =
	 * "application/vnd.company.app-v1+json")
	 * 
	 * V2: http://localhost:8080/person/accept
	 * 
	 * HEADER - Accept:application/vnd.company.app-v1+json
	 * 
	 * @GetMapping(path = "/person/accept", produces =
	 * "application/vnd.company.app-v2+json")
	 * 
	 
	 */
	
	@GetMapping(path="/person/accept",produces =
			 "application/vnd.company.app-v1+json")
	public PersonV1 getfirstVersionPersonByReqParaAcceptHeader() {
		return new PersonV1("Vandana Jogi");
	}
	
	
	@GetMapping(path="/person/accept2",produces =
			 "application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionPersonByReqParaAcceptHeader() {
		return new PersonV2(new Name("Pari", "Jogi"));
	}
	
	
	/*
	 * V1 Response
	 * 
	 * { "name": "Bob Charlie" } V2 Response
	 * 
	 * { "name": { "firstName": "Bob", "lastName": "Charlie" } }
	 */
}
