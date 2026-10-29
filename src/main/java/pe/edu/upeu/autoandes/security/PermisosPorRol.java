package pe.edu.upeu.autoandes.security;
import org.springframework.stereotype.Component; import java.util.*;
@Component public class PermisosPorRol {
 private static final Set<String> TODOS=Set.of("SERVICIO_LEER","SERVICIO_ESCRIBIR","VEHICULO_LEER","VEHICULO_ESCRIBIR","ORDEN_LEER","ORDEN_REGISTRAR","ORDEN_ANULAR","REPORTE_VER");
 private static final Map<String,Set<String>> MAP=Map.of("ADMIN",TODOS,"ASESOR",Set.of("SERVICIO_LEER","VEHICULO_LEER","VEHICULO_ESCRIBIR","ORDEN_LEER","ORDEN_REGISTRAR"),"JEFE_TALLER",Set.of("SERVICIO_LEER","SERVICIO_ESCRIBIR","VEHICULO_LEER","ORDEN_LEER","ORDEN_ANULAR","REPORTE_VER"));
 public Set<String> paraRoles(Collection<String> roles){Set<String> result=new TreeSet<>(); for(String r:roles)result.addAll(MAP.getOrDefault(r.toUpperCase(Locale.ROOT),Set.of())); return result;}
 public Set<String> roles(){return MAP.keySet();}
}
