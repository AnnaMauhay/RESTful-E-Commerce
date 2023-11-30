package futurewomen;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/products")
public class ProductResource {
    ProductDao productDao = new ProductDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts() {
        return productDao.getProducts();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProduct(@PathParam("id") int id) {
        return productDao.getProductByID(id);
    }

    @GET
    @Path("/category")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProductByCategory(@QueryParam("type") String category) {
        return productDao.getProductByCategory(category);
    }

    @GET
    @Path("/price")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductByPriceRange(@QueryParam("start") float start,
                                                @QueryParam("end") float end) {
        if (end >= start) {
            return Response.ok()
                    .entity(productDao.getProductByPrice(start, end))
                    .build();
        }
        else{
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid request. End value should not be less than the start value")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product) {
        if (productDao.getProducts().contains(product)) {
            return Response.status(Response.Status.NOT_ACCEPTABLE.getStatusCode())
                    .entity("Product already exists").build();
        }
        int numOfRows = productDao.addProduct(product);
        return Response.status(Response.Status.CREATED.getStatusCode())
                .entity("Created "+numOfRows+" new product").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") int id, Product product) {
        if (productDao.getProductByID(id)==null) {
            return Response.status(Response.Status.CREATED.getStatusCode())
                    .entity("Product ID does not exist.").build();
        }
        else if(product.getId()!=id){
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode())
                    .entity("Provided ID and product ID do not match.").build();
        }
        int numOfRows = productDao.updateProduct(id, product);
        return Response.status(Response.Status.OK.getStatusCode())
                .entity("Updated "+numOfRows+" product/s.").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") int id) {
        int numOfRows =productDao.deleteProduct(id);
        if (numOfRows==0){
            return Response.status(Response.Status.NOT_ACCEPTABLE.getStatusCode())
                    .entity("No product matches the provided ID.").build();
        }
        return Response.status(Response.Status.OK.getStatusCode())
                .entity(numOfRows+" product (with ID="+id+") is deleted.").build();
    }
}
