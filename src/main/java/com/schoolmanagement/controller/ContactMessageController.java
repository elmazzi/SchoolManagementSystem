package com.schoolmanagement.controller;

import com.schoolmanagement.payload.request.ContactMessageRequest;
import com.schoolmanagement.payload.response.ContactMessageResponse;
import com.schoolmanagement.payload.response.ResponseMessage;
import com.schoolmanagement.service.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("contactMessages")
@RequiredArgsConstructor
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    //Sisteme kayit olsun olmasin bu endpointe herkes ulasabilir
    //Not: save() *********************************************************************************************************************************
    @PostMapping("/save")
    public ResponseMessage<ContactMessageResponse> save(@Valid @RequestBody ContactMessageRequest contactMessageRequest) {

        return contactMessageService.save(contactMessageRequest);

    }

    
    
    //Not: getAll() ********************************************************************************************************************************
    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANTMANAGER')") //Belirttigimiz kullanicilar yetkilendirilsin
    public Page<ContactMessageResponse> getAll( //Birden fazla mesaj olabileceginde getAll methodunda Page yapisi kullanacagiz
                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size,
                                                @RequestParam(value = "sort", defaultValue = "date") String sort,
                                                @RequestParam(value = "type", defaultValue = "desc") String type
    ) {
        return contactMessageService.getAll(page, size, sort, type);
    }

    //Not: searchByEmail() *************************************************************************************************************************
    @GetMapping("/searchByEmail")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANTMANAGER')") //Belirttigimiz kullanicilar yetkilendirilsin
    public Page<ContactMessageResponse> searchByEmail(
                                                       @RequestParam(value = "email") String email,
                                                       @RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size,
                                                       @RequestParam(value = "sort", defaultValue = "date") String sort,
                                                       @RequestParam(value = "type", defaultValue = "desc") String type
    ) {
        return contactMessageService.searchByEmail(email,page,size,sort,type);
    }

    //Not: searchBySubject() ***********************************************************************************************************************
    @GetMapping("/searchBySubject")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANTMANAGER')") //Belirttigimiz kullanicilar yetkilendirilsin
    public Page<ContactMessageResponse> searchBySubject(
                                                       @RequestParam(value = "subject") String subject,
                                                       @RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size,
                                                       @RequestParam(value = "sort", defaultValue = "date") String sort,
                                                       @RequestParam(value = "type", defaultValue = "desc") String type
    ) {
        return contactMessageService.searchBySubject(subject,page,size,sort,type);
    }

}

// ÖDEV : POSTMAN de END-POINTLER test edilecek

























