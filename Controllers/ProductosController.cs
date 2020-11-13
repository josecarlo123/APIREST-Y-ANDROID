using Microsoft.AspNetCore.Mvc;
using System.Linq;
using System.Threading.Tasks;
using proyecto_web.Models;
namespace proyecto_web.Controllers{
 
    [Route("api/[controller]")]
    public class ProductosController : Controller {
      private Conexion dbConexion;
      public ProductosController(){
          dbConexion = Conectar.Create();

      }

      
      [HttpGet]
      public ActionResult Get(){
          return Ok(dbConexion.Productos.ToArray());
         //C:\Users\i14i34500w10\Desktop\Nueva carpeta\Proyecto\web\carpeta de imagenes.get 
      }


    [HttpGet ("{id}")]

    public async Task<ActionResult> Get(int id){
        var produdctos = await dbConexion.Productos.FindAsync(id);
        if(produdctos != null){
            return Ok(produdctos);
        }else{
            return NotFound();
        }
    }

[HttpPost]

public async Task<ActionResult> Post([FromBody] Productos productos){
if(!ModelState.IsValid)
return BadRequest();
dbConexion.Productos.Add(productos);
await dbConexion.SaveChangesAsync();
return Created("api/Productos", productos);
}

[HttpPut ("{id}")]

public async Task<ActionResult> Put(int id, [FromBody] Productos productos){

    var r_producto = dbConexion.Productos.SingleOrDefault(a => a.id_producto == id );
    if(r_producto != null && ModelState.IsValid){
        dbConexion.Entry(r_producto).CurrentValues.SetValues(productos);
        await dbConexion.SaveChangesAsync();

        return Ok();
    }else{
        return BadRequest();
    }
}

[HttpDelete("{id}")]
public async Task<ActionResult> Delete(int id){
    var empleados = dbConexion.Productos.SingleOrDefault(a => a.id_producto == id);
    if(empleados != null){
        dbConexion.Productos.Remove(empleados);
         await dbConexion.SaveChangesAsync();
        return Ok();

    }else{
        return NotFound();
    }
}

    }

 

}