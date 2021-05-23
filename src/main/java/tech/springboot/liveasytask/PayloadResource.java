package tech.springboot.liveasytask;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.springboot.liveasytask.model.Payload;
import tech.springboot.liveasytask.service.PayloadService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payload")
public class PayloadResource {
    private final PayloadService payloadService;

    public PayloadResource(PayloadService payloadService){
        this.payloadService=payloadService;
    }

    @PostMapping("/load")
    public Map<String,Object> addPayload(@RequestBody Payload payload){
        Map<String,Object> map = new HashMap<>();
        try{
            Payload newpayload = payloadService.addPayload(payload);
            map.put("payload",payload);
            map.put("Response","Loads details added successfully.");
        }catch(Exception e){
            map.put("message","Invalid load details");
            map.put("status","failed");
        }
        return map;
    }

    @GetMapping("/load/{id}")
    public Map<String,Object> getPayload(@PathVariable("id") Long id){
        Map<String,Object> map = new HashMap<>();
        try{
            Payload payload = payloadService.findPayloadById(id);
            map.put("data",payload);
            map.put("status","success");
        }catch(Exception e){
            map.put("message",e.getMessage());
            map.put("status","failed");
        }
        return map;
    }

    @PutMapping("/load/{id}")
    public Map<String,Object> updatePayload(@PathVariable("id") Long id, @RequestBody Payload payload){
        Map<String,Object> map = new HashMap<>();
        try{
            Payload updatepayload = payloadService.updatePayload(id,payload);
            map.put("data",updatepayload);
            map.put("status","success");
        }catch(Exception e){
            map.put("message","Invalid load details");
            map.put("status","failed");
        }
        return map;
    }

    @DeleteMapping("/load/{id}")
    public ResponseEntity<?> deletePayload(@PathVariable("id") Long id){
        payloadService.deletePayload(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/load")
    public Map<String,Object> getLoads(@RequestParam("id") String id){
        Map<String,Object> map = new HashMap<>();
        List<Payload> loads=payloadService.getAllLoads(id);
        map.put("Response",loads);
        map.put("status","success");
        return map;
    }
}
