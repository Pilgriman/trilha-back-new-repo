package trilha.back.financys.controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import trilha.back.financys.entities.Category;
import trilha.back.financys.service.CategoryService;


import javax.validation.Valid;
import java.util.List;


@RestController
public class CategoryController {

   @Autowired
   private CategoryService categoryService;

   @PostMapping(path = "/categorias/adicionar")
   public ResponseEntity<Void>createNewCategory(@Valid @RequestBody Category category, UriComponentsBuilder uriComponentsBuilder){
       Long id = categoryService.createNewCategory(category);

       UriComponents uriComponents = uriComponentsBuilder.path("/categorias/{id}").buildAndExpand(id);

       HttpHeaders headers = new HttpHeaders();

       headers.setLocation(uriComponents.toUri());

       return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

   }

   @GetMapping(path = "/categorias/consultar")
   public ResponseEntity<List<Category>> getAllCategories(){ return ResponseEntity.ok(categoryService.getAllCategories());

   }
    @GetMapping("categorias/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }


    @PutMapping("categorias/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @Valid @RequestBody Category categoryRequestDto){
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryRequestDto));
    }

    @DeleteMapping("categorias/{id}")
    public ResponseEntity<Void>deleteCategoryById(@PathVariable("id") Long id){
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }


    }





