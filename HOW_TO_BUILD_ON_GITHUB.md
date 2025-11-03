# How to Build SmoothMC Using GitHub Actions (No Computer Needed!)

Since you don't have a computer yet, you can use **GitHub's free cloud build service** to compile the mod for you!

## Step-by-Step Guide:

### 1. Create a GitHub Account (FREE)
- Go to https://github.com
- Click "Sign up"
- Create a free account (only takes 2 minutes)

### 2. Upload This Project to GitHub

**Option A: Using Replit (Easiest)**
1. In Replit, go to the left sidebar
2. Click on "Version Control" (Git icon)
3. Click "Create a Git Repo"
4. Push to GitHub following the prompts

**Option B: Manual Upload**
1. On GitHub.com, click the "+" icon → "New repository"
2. Name it "SmoothMC"
3. Click "Create repository"
4. Download this Replit project as a ZIP
5. Extract the ZIP on your TV Box
6. On the GitHub page, click "uploading an existing file"
7. Drag all the files from the extracted folder
8. Click "Commit changes"

### 3. GitHub Builds Your Mod Automatically!

Once uploaded, GitHub will:
- Detect the `.github/workflows/build.yml` file
- Automatically install Java 8
- Run `setupDecompWorkspace` (downloads and decompiles Minecraft)
- Build your mod
- Save the JAR file for you to download

### 4. Download Your Compiled JAR

1. Go to your repository on GitHub
2. Click the "Actions" tab at the top
3. Click on the most recent build (should be green ✓)
4. Scroll down to "Artifacts"
5. Click "SmoothMC-mod" to download
6. Extract the ZIP - inside is your `SmoothMC-1.0.0.jar`!

### 5. Install in Minecraft

1. Copy `SmoothMC-1.0.0.jar` to your `.minecraft/mods/` folder
2. Launch Minecraft 1.8.9 with Forge
3. Enjoy reduced lag in PVP!

---

## Triggering Builds Manually

If you want to rebuild the mod:
1. Go to Actions tab
2. Click "Build SmoothMC Mod" on the left
3. Click "Run workflow" button
4. Click the green "Run workflow" button
5. Wait for it to finish, then download from Artifacts

---

## Build Time

**First build**: 10-15 minutes (downloads Minecraft + decompiles it)
**Later builds**: 5-10 minutes

GitHub gives you **2,000 free build minutes per month** - more than enough!

---

## Troubleshooting

**Build fails?**
- Check the "Actions" logs to see the error
- Common issues:
  - Forgot to upload `gradle/wrapper/gradle-wrapper.jar`
  - Solution: Make sure ALL files are uploaded

**Can't find Artifacts?**
- Scroll to the very bottom of the completed build
- Look for "Artifacts" section
- Should see "SmoothMC-mod" with a download icon

---

## Questions?

This method is **completely free** and works from any device with a web browser (including your TV Box). You never need to install Java yourself - GitHub's servers do all the work!

The first build takes longer because it downloads and decompiles Minecraft, but after that, builds are fast. Every time you make changes to the code, just push to GitHub and it rebuilds automatically.
