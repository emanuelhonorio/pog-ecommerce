# Pog Ecommerce

**Pog Ecommerce** é um projeto desenvolvido por mim para meu portifólio. Na qual é um ecommerce para uma loja fictícia de produtos esportivos.

## Live Demo

Link : https://pog-ecommerce-ui.herokuapp.com/

## API Swagger Documentation

Link: https://pog-ecommerce-api.herokuapp.com/docs.html

## Project Structure

```
📦main
 ┣ 📂java
 ┃ ┗ 📂com
 ┃ ┃ ┗ 📂emanuelhonorio
 ┃ ┃ ┃ ┗ 📂pogecommerce
 ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthenticationManagerBean.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthorizationServerConfig.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ResourceServerConfig.java
 ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┣ 📂validation
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂annotation
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Cep.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂validator
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CepValidator.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CompraDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CreateCorDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CreateEstoqueDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CreateFotoDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CreateProdutoDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CreateTamanhoDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜EnderecoDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ItemCompraDTO.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜UsuarioDTO.java
 ┃ ┃ ┃ ┃ ┣ 📂error
 ┃ ┃ ┃ ┃ ┃ ┣ 📂exceptions
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OutOfStockException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ResourceAlreadyExistsException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ResourceNotFoundException.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ResourceOwnerException.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂response
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ErrorResponse.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜GlobalExceptionHandler.java
 ┃ ┃ ┃ ┃ ┣ 📂filter
 ┃ ┃ ┃ ┃ ┃ ┗ 📜CorsFilter.java
 ┃ ┃ ┃ ┃ ┣ 📂model
 ┃ ┃ ┃ ┃ ┃ ┣ 📂enums
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜StatusCompra.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Categoria.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Compra.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Cor.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Endereco.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Estoque.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Foto.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ItemCompra.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Produto.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Tamanho.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜Usuario.java
 ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CategoriaRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CompraRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CorRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜EnderecoRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜EstoqueRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜FotoRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ProdutoRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜TamanhoRepository.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜UsuarioRepository.java
 ┃ ┃ ┃ ┃ ┣ 📂resource
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CategoriaResource.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CompraResource.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CorResource.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜EnderecoResource.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜EstoqueResource.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜IndexResource.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜MeResource.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ProdutoResource.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜RegisterResource.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜TamanhoResource.java
 ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┣ 📂filter
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ProdutoFilter.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜AppUserDetailsService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CategoriaService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CompraService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜EnderecoService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜EstoqueService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ProdutoService.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜UsuarioService.java
 ┃ ┃ ┃ ┃ ┣ 📜PasswordEncoderGenerator.java
 ┃ ┃ ┃ ┃ ┗ 📜PogEcommerceApplication.java
 ┗ 📂resources
 ┃ ┣ 📂db
 ┃ ┃ ┗ 📂migration
 ┃ ┃ ┃ ┣ 📜V001__create_base_tables.sql
 ┃ ┃ ┃ ┗ 📜V002__insert_admin_to_users_table.sql
 ┃ ┣ 📜application-prod.properties
 ┃ ┗ 📜application.properties
```
