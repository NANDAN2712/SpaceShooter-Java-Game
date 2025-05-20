# 🚀 Java Space Shooter Game

A 2D space shooter arcade game built in **Java Swing**, featuring:

- GUI-based gameplay
- Score tracking and saving to SQLite database
- High score table viewer
- Functional Pause/Resume/Restart
- Packaged as `.jar` and `.exe`

---

## 🎮 Features

| Feature             | Description                                     |
|---------------------|-------------------------------------------------|
| Bullet shooting     | Player can shoot bullets at enemies             |
| Score system        | Score increases as enemies are destroyed        |
| Power-ups           | Restore health by collecting powerups           |
| Game over prompt    | Asks for name and stores score in DB            |
| Score Viewer        | View all saved scores sorted in table format    |
| Executable          | Packaged as `.exe` using Launch4j               |

---

## 🧪 How to Run

### ▶️ JAR
Make sure Java 8+ is installed, then run:
```bash
java -jar SpaceShooter.jar
```

### ▶️ EXE (Recommended)
Double-click `SpaceShooter.exe`  
Ensure `sqlite-jdbc-3.49.1.0.jar` is in the same folder.

---

## 📸 Screenshots



---

## 🛠 Tools Used

- Java 8+
- Swing GUI
- SQLite with JDBC
- Launch4j (for `.exe`)
- Git + GitHub

---

## 📁 Folder Structure

```
📁 src/                  → Source code (.java files)
📁 lib/                  → JDBC driver
📄 SpaceShooter.jar      → Compiled JAR
📄 SpaceShooter.exe      → Windows executable
📄 scores.db             → Local DB file (auto-created)
```

---

## 👤 Developer

**Nandan and Arush **  
*Created for educational purposes — feel free to fork & modify!*

---

## 📌 License

This project is open-source and free to use.
