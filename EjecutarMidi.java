public class EjecutarMidi {
   public static void main(String[] args) throws Exception {
      UIPedirIp ip = new UIPedirIp();
      ip.show();

      while(!ip.esValido()) {
      }

      ClienteTCP z = ClienteTCP.getInstancia();
      z.conectar(ip.getHost(), ip.getName(), 9999);
      String f = z.EstadoServer();
      UIAplicacion ui = new UIAplicacion(z);
      z.registrar(ui);
      ui.show();
      ip.dispose();
   }
}
