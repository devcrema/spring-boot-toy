package devcrema.spring_boot_toy.controller;

import devcrema.spring_boot_toy.Api;
import devcrema.spring_boot_toy.store.Store;
import devcrema.spring_boot_toy.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Api.Store.STORES)
@RequiredArgsConstructor
public class GetStoreController {
    private final StoreRepository storeRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Store> getStores(@RequestParam("page") int page, @RequestParam("size") int size){
        return storeRepository.findAllBy(PageRequest.of(page, size));
    }
}
