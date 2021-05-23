package tech.springboot.liveasytask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.springboot.liveasytask.exception.UserNotFoundException;
import tech.springboot.liveasytask.model.Payload;
import tech.springboot.liveasytask.repo.PayloadRepo;

import java.util.List;
import java.util.UUID;

@Service
public class PayloadService {

    private final PayloadRepo payloadRepo;

    @Autowired
    public PayloadService(PayloadRepo payloadRepo){
        this.payloadRepo=payloadRepo;
    }



    public Payload addPayload(Payload payload){
        return payloadRepo.save(payload);
    }

    public Payload findPayloadById(Long id){
        return payloadRepo.findById(id).orElseThrow(()->new UserNotFoundException("Payload with loadId "+id+" was not found."));

    }

    public Payload updatePayload(Long id,Payload payload){
        Payload oldPayload=payloadRepo.findById(id).get();
        oldPayload.setLoadId(id);
        oldPayload.setComment(payload.getComment());
        oldPayload.setDate(payload.getDate());
        oldPayload.setLoadingPoint(payload.getLoadingPoint());
        oldPayload.setUnloadingPoint(payload.getUnloadingPoint());
        oldPayload.setNoOfTrucks(payload.getNoOfTrucks());
        oldPayload.setProductType(payload.getProductType());
        oldPayload.setTruckType(payload.getTruckType());
        oldPayload.setWeight(payload.getWeight());


        return payloadRepo.saveAndFlush(oldPayload);
    }

    public void deletePayload(Long id){
        payloadRepo.deleteById(id);
    }

    public List<Payload> getAllLoads(String id){
        return payloadRepo.findByShipperId(id);

    }
}
