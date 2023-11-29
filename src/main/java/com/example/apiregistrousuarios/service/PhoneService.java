package com.example.apiregistrousuarios.service;


import com.example.apiregistrousuarios.entity.Phone;
import com.example.apiregistrousuarios.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneService {
    @Autowired
    private PhoneRepository phoneRepository;

    public List<Phone> getAll(){
        return phoneRepository.getAll();
    }

    public Optional<Phone> getUser(int id){
        return phoneRepository.getUser(id);
    }

    public Phone save(Phone phone){
        if(phone.getId()==null){

            return phoneRepository.save(phone);
        }else{
            Optional<Phone> e= phoneRepository.getUser(phone.getId());
            if(e.isEmpty()){
                return phoneRepository.save(phone);
            }else{
                return phone;
            }
        }
    }

    public Phone update(Phone phone){
        if(phone.getId()!=null){
            return phoneRepository.save(phone);
        }else{
            Optional<Phone> e= phoneRepository.getUser(phone.getId());
            if(!e.isEmpty()){
                if(phone.getContrycode()!=null){
                    e.get().setContrycode(phone.getContrycode());
                }
                if(phone.getCitycode()!=null){
                    e.get().setCitycode(phone.getCitycode());
                }
                if(phone.getNumber()!=null){
                    e.get().setNumber(phone.getNumber());
                }
                if(phone.getUsser()!=null){
                    e.get().setUsser(phone.getUsser());
                }

                phoneRepository.save(e.get());
                return e.get();
            }else{
                return phone;
            }
        }
    }

    public boolean deleteUser(int id){
        Boolean aBoolean=getUser(id).map(User -> {
            phoneRepository.delete(User);
            return true;
        }).orElse(aBoolean=false);

        return aBoolean;
    }

    //Customs



}

