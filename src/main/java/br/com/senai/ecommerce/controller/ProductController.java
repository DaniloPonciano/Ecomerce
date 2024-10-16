package br.com.senai.ecommerce.controller;

import br.com.senai.ecommerce.entities.Product;
import br.com.senai.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //suporta apenas dados
@RequestMapping("/product") // localhost:8080/product
public class ProductController {
    //injeção de dependência, criar um objeto
    @Autowired
    private ProductRepository productRepository;

    //metodo para criar um product
    @PostMapping(value="/createProduct",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product createProduct(@RequestBody Product product) {

        Product newProduct = new Product();

        newProduct.setAndar(product.getAndar());
        newProduct.setCor1(product.getCor1());
        newProduct.setCor2(product.getCor2());
        newProduct.setCor3(product.getCor3());
        newProduct.setDesenho1(product.getDesenho1());
        newProduct.setDesenho2(product.getDesenho2());
        newProduct.setDesenho3(product.getDesenho3());

        return productRepository.save(newProduct);
    }


    // listar todos os products do banco de dados
    @GetMapping(value = "/producties", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProducts(){
        // SELECT * FROM product
        return productRepository.findAll();
    }

    //pegar um product pelo seu id
    @GetMapping(value = "/pedido/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProductById(@PathVariable Long id){
        return productRepository.findById(id).orElse(null);
    }

    // deleta um product pelo seu id
    @DeleteMapping(value = "/pedido/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProduct(@PathVariable Long id){

        Product getProduct = productRepository.findById(id).orElseThrow();
        productRepository.delete(getProduct);

        //return getProduct;
    }

    //atualiza informações do product pelo seu id
    @PutMapping(value = "/pedido/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@PathVariable Long id,
                           @RequestBody Product product){

        if(product != null){
            product.setId(id);
            return productRepository.save(product);
        }
        return null;
    }
}

