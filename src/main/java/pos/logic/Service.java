package pos.logic;

import pos.data.Data;
import pos.data.XmlPersister;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private static Service theInstance;

    public static Service instance(){
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }
    private Data data;

    private Service(){
        try{
            data= XmlPersister.instance().load();
        }
        catch(Exception e){
            data =  new Data();
        }
    }

    public void stop(){
        try {
            XmlPersister.instance().store(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//================= CLIENTES ============

    public void create(Cliente e) throws Exception{
        Cliente result = data.getClientes().stream().filter(i->i.getId().equals(e.getId())).findFirst().orElse(null);
        if (result==null) data.getClientes().add(e);
        else throw new Exception("Cliente ya existe");
    }

    public Cliente read(Cliente e) throws Exception{
        Cliente result = data.getClientes().stream().filter(i->i.getId().equals(e.getId())).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Cliente no existe");
    }

    public void update(Cliente e) throws Exception{
        Cliente result;
        try{
            result = this.read(e);
            data.getClientes().remove(result);
            data.getClientes().add(e);
        }catch (Exception ex) {
            throw new Exception("Cliente no existe");
        }
    }

    public void delete(Cliente e) throws Exception{
        data.getClientes().remove(e);
    }

    public List<Cliente> search(Cliente e){
        if (e.getId() != null && !e.getId().isEmpty()) {
            return data.getClientes().stream()
                    .filter(i -> i.getId().contains(e.getId()))
                    .sorted(Comparator.comparing(Cliente::getNombre))
                    .collect(Collectors.toList());
        }
        else {
            return data.getClientes().stream()
                    .filter(i -> i.getNombre().contains(e.getNombre()))
                    .sorted(Comparator.comparing(Cliente::getNombre))
                    .collect(Collectors.toList());
        }
    }

    public List<Cliente> getClientes() {
        return data.getClientes();
    }

    //================= CAJEROS ============
    public void create(Cajero e) throws Exception{
        Cajero result = data.getCajeros().stream().filter(i->i.getId().equals(e.getId())).findFirst().orElse(null);
        if (result==null) data.getCajeros().add(e);
        else throw new Exception("Cajero ya existe");
    }

    public Cajero read(Cajero e) throws Exception{
        Cajero result = data.getCajeros().stream().filter(i->i.getId().equals(e.getId())).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Cliente no existe");
    }

    public void update(Cajero e) throws Exception{
        Cajero result;
        try{
            result = this.read(e);
            data.getCajeros().remove(result);
            data.getCajeros().add(e);
        }catch (Exception ex) {
            throw new Exception("Cajero no existe");
        }
    }

    public void delete(Cajero e) throws Exception{
        data.getCajeros().remove(e);
    }

    public List<Cajero> search(Cajero e){
        if (e.getId() != null && !e.getId().isEmpty()) {
            return data.getCajeros().stream()
                    .filter(i -> i.getId().contains(e.getId()))
                    .sorted(Comparator.comparing(Cajero::getId))
                    .collect(Collectors.toList());
        }

        else {
            return data.getCajeros().stream()
                    .filter(i -> i.getNombre().contains(e.getNombre()))
                    .sorted(Comparator.comparing(Cajero::getNombre))
                    .collect(Collectors.toList());
        }
    }

    public List<Cajero> getCajeros() {
        return data.getCajeros();
    }

    //================= CATEGORIAS ============
    public void create(Categoria e) throws Exception{
        Categoria result = data.getCategorias().stream().filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result==null) data.getCategorias().add(e);
        else throw new Exception("Categoria ya existe");
    }

    public Categoria read(Categoria e) throws Exception{
        Categoria result = data.getCategorias().stream().filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Categoria no existe");
    }
    public List<Categoria> search(Categoria e){
        if (e.getCodigo() != null && !e.getCodigo().isEmpty()) {
            return data.getCategorias().stream().filter(i->i.getCodigo().equals(e.getCodigo())).collect(Collectors.toList());
        }else{
            return new ArrayList<>();
        }
    }

    public List<Categoria> getCategorias() {
        return data.getCategorias();  // Retorna la lista completa de productos
    }


    //================= PRODUCTOS ============
    public void create(Producto e) throws Exception{
        Producto result = data.getProductos().stream().filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result==null) data.getProductos().add(e);
        else throw new Exception("Producto ya existe");
    }

    public Producto read(Producto e) throws Exception{
        Producto result = data.getProductos().stream().filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Producto no existe");
    }

    public void update(Producto e) throws Exception{
        Producto result;
        try{
            result = this.read(e);
            data.getProductos().remove(result);
            data.getProductos().add(e);
        }catch (Exception ex) {
            throw new Exception("Producto no existe");
        }
    }

    public void delete(Producto e) throws Exception{
        data.getProductos().remove(e);
    }

    public List<Producto> search(Producto e){
        if (e.getCodigo() != null && !e.getCodigo().isEmpty()) {
            return data.getProductos().stream()
                    .filter(i -> i.getCodigo().contains(e.getCodigo()))
                    .sorted(Comparator.comparing(Producto::getCodigo))
                    .collect(Collectors.toList());
        }

        else {
            return data.getProductos().stream()
                    .filter(i -> i.getDescripcion().contains(e.getDescripcion()))
                    .sorted(Comparator.comparing(Producto::getDescripcion))
                    .collect(Collectors.toList());
        }
    }

    public List<Producto> getProductos(){
        return data.getProductos();
    }

    //================= FACTURAS ============

    public void create(Factura e) throws Exception {
        Factura result = data.getFacturas().stream().filter(i -> i.getNumero().equals(e.getNumero())).findFirst().orElse(null);
        if (result == null) data.getFacturas().add(e);
        else throw new Exception("Factura ya existe");
    }

    public Factura read(Factura e) throws Exception{
        Factura result = data.getFacturas().stream().filter(i -> i.getNumero().equals(e.getNumero())).findFirst().orElse(null);
        if (result != null) return result;
        else throw new Exception("Factura no existe");
    }

    public void update(Factura e) throws Exception{
        Factura result;
        try {
            result = this.read(e);
            data.getFacturas().remove(result);
            data.getFacturas().add(e);
        } catch (Exception ex) {
            throw new Exception("Factura no existe");
        }
    }

    public void delete(Factura e) throws Exception{
        data.getFacturas().remove(e);
    }

    public List<Factura> search(Factura e){
        if (e.getNumero() != null && !e.getNumero().isEmpty()) {
            return data.getFacturas().stream()
                    .filter(i -> i.getNumero().contains(e.getNumero()))
                    .sorted(Comparator.comparing(Factura::getNumero))
                    .collect(Collectors.toList());
        }
        else {
            return data.getFacturas().stream()
                    .filter(i -> i.getCliente().getNombre().contains(e.getCliente().getNombre()))
                    .sorted(Comparator.comparing(f -> f.getCliente().getNombre()))
                    .collect(Collectors.toList());
        }
    }

    public List<Factura> getFacturas(){
        return data.getFacturas();
    }

    //================= LINEAS ===================
    public void create(Linea e) throws Exception{
        Linea result = data.getLineas().stream().filter(i -> i.getNumero().equals(e.getNumero())).findFirst().orElse(null);
        if(result==null) data.getLineas().add(e);
        else throw new Exception("Linea ya existe");
    }

    public Linea read(Linea e) throws Exception{
        Linea result = data.getLineas().stream().filter(i -> i.getNumero().equals(e.getNumero())).findFirst().orElse(null);
        if (result != null) return result;
        else throw new Exception("Linea no existe");
    }

    public void update(Linea e) throws Exception{
        Linea result;
        try {
            result = this.read(e);
            data.getLineas().remove(result);
            data.getLineas().add(e);
        } catch (Exception ex) {
            throw new Exception("Linea no existe");
        }
    }

    public void delete(Linea e) throws Exception{
        data.getFacturas().remove(e);
    }

    public List<Linea> search(Linea e){
        if (e.getNumero() != null && !e.getNumero().isEmpty()) {
            return data.getLineas().stream()
                    .filter(i -> i.getNumero().contains(e.getNumero()))
                    .sorted(Comparator.comparing(Linea::getNumero))
                    .collect(Collectors.toList());
        }
        else {
            return data.getLineas().stream()
                    .filter(i -> i.getProducto().getDescripcion().contains(e.getProducto().getDescripcion()))
                    .sorted(Comparator.comparing(f -> f.getProducto().getCodigo()))
                    .collect(Collectors.toList());
        }
    }

    public List<Linea> getLineas(){
        return data.getLineas();
    }

}
