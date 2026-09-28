# ☕ Programación orientada a objetos y excepciones en Java

Tres programas de la materia **Paradigmas de Programación** que practican **herencia, clases abstractas, polimorfismo y manejo de excepciones** en Java. Incluyen **26 pruebas automáticas**.

> Contexto: [PENDIENTE: semestre en que cursaste Paradigmas de Programación]

## 📦 Contenido

### 1. `biblioteca`: préstamo de materiales

Una clase abstracta `Material` y tres tipos concretos: `Libro`, `Revista` y `Tesis`.

```
Material (abstracta)
├── Libro    → número de páginas
├── Revista  → número de edición
└── Tesis    → universidad
```

- Se puede **prestar** y **devolver** un material. Prestarlo dos veces lanza `MaterialNoDisponibleException`.
- Los constructores **validan los datos**: título y autor no vacíos, año entre 1 y el año actual. Si algo está mal, lanzan `DatoInvalidoException`.

### 2. `rentavehiculos`: renta de vehículos

La misma idea con `Vehiculo` → `Automovil`, `Motocicleta` y `Camion`. Se pueden **rentar** y **devolver**, y los *setters* también validan los datos.

### 3. `excepciones`: catálogo de 15 excepciones comunes

Provoca a propósito 15 excepciones de Java (`ArithmeticException`, `NullPointerException`, `ClassCastException`, `ConcurrentModificationException`…) y **verifica automáticamente** que cada caso lance la excepción correcta:

```
 1.  OK     División entre cero            -> ArithmeticException
 2.  OK     Texto nulo                     -> NullPointerException
 ...
14.  OK     Modificación concurrente       -> ConcurrentModificationException
15.  OK     Recurso no encontrado          -> MissingResourceException

Resultado: 15 de 15 casos correctos
```

## 🧠 Decisiones de diseño

- **Patrón Template Method:** `prestar()`/`rentar()` y `devolver()` están escritos **una sola vez** en la clase abstracta (y son `final`). Cada subclase solo implementa `getTipo()` y `detalle()`. Antes, cada subclase repetía la misma lógica.
- **Objetos siempre válidos:** se eliminaron los constructores vacíos que permitían crear materiales sin título ni año.
- **`toString()`** muestra todos los datos, incluidos los propios de cada subclase.
- **Excepciones verificadas (*checked*)** para errores que el programa debe atender (dato inválido, material no disponible) y **no verificadas** para errores de uso (`IllegalStateException` al devolver algo que no estaba prestado).

## 🛠️ Tecnologías

- Java 17 o superior (usa `record` y `String.isBlank()`)
- Sin librerías externas

## ▶️ Cómo ejecutarlo

```bash
git clone https://github.com/arumando/java-poo-excepciones.git
cd java-poo-excepciones
```

**Compilar** (Git Bash, Linux o macOS):

```bash
javac -encoding UTF-8 -d out $(find src test -name "*.java")
```

**Compilar** (PowerShell en Windows):

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java src, test).FullName
```

**Ejecutar** cada programa:

```bash
java -cp out biblioteca.Main
java -cp out rentavehiculos.Main
java -cp out excepciones.Main
```

**Ejecutar las pruebas:**

```bash
java -cp out pruebas.Pruebas
```

> En NetBeans o IntelliJ: crea un proyecto Java y marca `src` como carpeta de código fuente y `test` como carpeta de pruebas.

## 📸 Capturas

[PENDIENTE: captura de la salida de `excepciones.Main`]

[PENDIENTE: captura de las pruebas pasando]

## 📚 Qué aprendí

<!-- Revisa esta lista y escríbela con tus propias palabras. -->
- Modelar jerarquías con herencia y clases abstractas.
- Crear excepciones propias y decidir cuándo usar excepciones verificadas o no verificadas.
- Evitar código repetido moviendo la lógica común a la clase padre (Template Method).
- Un detalle curioso: borrar un elemento de una lista mientras se recorre con `for-each` **no siempre** lanza `ConcurrentModificationException`; con 2 elementos el ciclo termina antes de detectarlo.

## 👤 Autor

**José Armando García Bandera** — [github.com/arumando](https://github.com/arumando)
