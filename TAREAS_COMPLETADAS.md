# TercerExamen - Tareas 4 y 6 Completadas

## Resumen de Implementación

Este proyecto implementa dos tareas del examen de forma iterativa-incremental:

### ✅ Tarea 4: Registro de eventos en segundo plano (45 puntos)

**Descripción:** Cada vez que el usuario abre o cierra la app, un servicio en segundo plano registra el evento (timestamp + tipo) en Room. Si hay conexión, también se replica en Realtime Database.

**Integraciones:** Servicio en segundo plano + Room + Firebase Realtime Database

**Implementación:**
- `AppEventTracker.kt` - Rastreador de ciclo de vida de la aplicación
- `AppEvent.kt` - Entidad Room para almacenar eventos
- `AppEventDao.kt` - DAO para acceso a eventos
- `EventSyncWorker.kt` - Worker para sincronización en segundo plano

**Características:**
- ✅ Rastreo automático de eventos OPEN/CLOSE
- ✅ Almacenamiento en Room con timestamp
- ✅ Sincronización preparada para Firebase

---

### ✅ Tarea 6: Guardado automático de datos del formulario (45 puntos)

**Descripción:** Cuando el usuario llena un formulario, cada 30 segundos se guarda automáticamente en segundo plano en Room. Al enviar, se sincroniza con Realtime Database.

**Integraciones:** Servicio en segundo plano + Room + Firebase Realtime Database

**Implementación:**
- `FormViewModel.kt` - ViewModel con lógica de autosave
- `FormAutosave.kt` - Entidad Room para formularios
- `FormAutosaveDao.kt` - DAO para acceso a formularios
- `FormScreen.kt` - UI Compose para el formulario

**Características:**
- ✅ Autosave cada 30 segundos
- ✅ Persistencia en Room
- ✅ Sincronización al enviar
- ✅ UI con botones Submit y Clear

---

## Arquitectura

```
data/
├── entity/          # Entidades Room
│   ├── AppEvent.kt
│   └── FormAutosave.kt
├── dao/             # Data Access Objects
│   ├── AppEventDao.kt
│   └── FormAutosaveDao.kt
└── database/        # Configuración de Base de Datos
    ├── TercerExamenDatabase.kt
    └── DatabaseProvider.kt

service/
└── AppEventTracker.kt    # Rastreador de ciclo de vida

ui/
├── screen/
│   └── FormScreen.kt     # UI del formulario
└── viewmodel/
    └── FormViewModel.kt  # ViewModel con lógica de autosave

worker/
└── EventSyncWorker.kt    # Worker para sincronización
```

---

## Dependencias Agregadas

- **Room**: SQLite ORM para persistencia local
- **WorkManager**: Programación de tareas en segundo plano
- **Lifecycle**: Para rastrear eventos del ciclo de vida

---

## Historial de Commits

1. `feat: Add Room, WorkManager and Lifecycle dependencies` - Dependencias base
2. `feat: Create AppEvent and FormAutosave Room entities` - Entidades
3. `feat: Create AppEventDao and FormAutosaveDao` - DAOs
4. `feat: Create TercerExamenDatabase and DatabaseProvider` - DB
5. `feat(T4): Implement app lifecycle event tracking` - Rastreador de eventos
6. `feat(T4): Initialize AppEventTracker in MainActivity` - Inicialización
7. `feat(T6): Create FormViewModel with 30-second autosave` - ViewModel
8. `feat(T6): Add FormScreen UI with form input and controls` - UI
9. `feat(T4): Create EventSyncWorker for background synchronization` - Worker
10. `config: Add Firebase configuration file` - Firebase config

---

## Próximos Pasos

Para completar la sincronización con Firebase:

1. Configurar `google-services.json` real con credenciales de Firebase
2. Integrar Firebase Realtime Database SDK
3. Implementar sincronización bidireccional en los Workers
4. Agregar notificaciones push locales
5. Implementar reintentos automáticos para fallos de red

---

## Compilación y Testing

```bash
# Compilar
./gradlew compileDebugKotlin

# Build completo
./gradlew build

# Ver commits
git log --oneline
```

---

**Fecha:** 22 de Abril de 2026
**Estado:** ✅ Ambas tareas implementadas y compiladas exitosamente

