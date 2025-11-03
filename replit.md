# SmoothMC - Minecraft Forge 1.8.9 Mod

## Overview
SmoothMC is a client-side optimization mod for Minecraft 1.8.9 that combines two performance-enhancing systems:

1. **ChunkCache** - Caches loaded chunks to local JSON files and reloads them to reduce chunk reload stutter
2. **NoHitLag** - Removes client-side lag spikes when the player is hit by disabling hurt camera effects

## Project Structure
```
SmoothMC/
├── src/main/java/com/smoothmc/
│   ├── SmoothMC.java              # Main mod class with @Mod annotation
│   ├── chunk/
│   │   └── ChunkCacheManager.java # Chunk caching system
│   └── hit/
│       └── NoHitLagHandler.java   # Hit lag removal system
├── src/main/resources/
│   └── mcmod.info                 # Mod metadata
├── build.gradle                   # Gradle build configuration
├── gradle.properties              # Gradle properties
└── settings.gradle                # Gradle settings
```

## Technologies
- **Minecraft**: 1.8.9
- **Forge**: 1.8.9-11.15.1.2318-1.8.9
- **Java**: 8 (JDK 1.8)
- **Build Tool**: Gradle 2.14 with ForgeGradle 2.1
- **Libraries**: Gson (for JSON chunk caching)

## Building the Mod
This project uses Gradle to build the mod. The build process will:
1. Download Minecraft and Forge dependencies
2. Deobfuscate Minecraft classes
3. Compile the mod sources
4. Package everything into a JAR file

To build:
```bash
./gradlew build
```

The compiled mod JAR will be located in `build/libs/SmoothMC-1.0.0.jar`

## Testing
To test this mod:
1. Build the project using `./gradlew build`
2. Copy the JAR file from `build/libs/` to your Minecraft `mods/` folder
3. Launch Minecraft 1.8.9 with Forge installed
4. Check the mod list to verify SmoothMC is loaded

## Features

### ChunkCache System
- Listens to chunk load/unload events
- Saves chunk data to JSON files in `.minecraft/smoothmc/chunks/`
- Loads cached chunk data when chunks are reloaded
- Uses Gson for JSON serialization

### NoHitLag System
- Monitors player hurt events
- Removes hurt time and camera shake when player is damaged
- Reduces client-side lag spikes from damage effects

## Development Notes
- This is a **client-side only** mod (clientSideOnly = true)
- Both systems register to the Forge event bus automatically
- Chunk cache files are stored in the Minecraft data directory
- TODO comments indicate areas for future implementation

## Recent Changes
- 2025-11-03: **FIXED** GitHub Actions build failure:
  - The original ForgeGradle 2.1-SNAPSHOT IS still available on https://maven.minecraftforge.net
  - Changed Gradle wrapper from 4.9 to 3.1 (recommended version for ForgeGradle 2.1)
  - Added Sponge Maven repository for additional dependencies
  - Updated Forge maven URL from HTTP to HTTPS (`https://maven.minecraftforge.net`)
  - Replaced deprecated `jcenter()` with `mavenCentral()`
  - Note: anatawa12's ForgeGradle fork doesn't support MC 1.8.9 (only 1.7.10 and 1.12.2)
- 2025-11-03: Initial project setup with Forge 1.8.9 MDK
- 2025-11-03: Implemented basic ChunkCacheManager with JSON caching
- 2025-11-03: Implemented NoHitLagHandler with hurt time reduction

## Build Configuration
This project uses GitHub Actions to build the mod automatically because Replit doesn't have Java 8 available. The build process:
1. Pushes code to GitHub
2. GitHub Actions automatically runs with Java 8
3. Builds the mod JAR file
4. Uploads the artifact for download

See `.github/workflows/build.yml` for the automated build configuration.
