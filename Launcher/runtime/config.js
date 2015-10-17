// ====== LAUNCHER CONFIG ====== //
var config = {
	dir: "sashok724", // Launcher directory
	title: "sashok724's Launcher", // Window title
	icons: [ "favicon.png" ], // Window icon paths

	// Auth config
	newsURL: "http://launcher.sashok724.net/", // News WebView URL
	linkText: "Бесплатные окна", // Text for link under "Auth" button
	linkURL: new java.net.URL("http://bit.ly/1SP0Rl8"), // URL for link under "Auth" button

	// Settings defaults
	settingsMagic: 0xBEEF, // Ancient magic, don't touch
	autoEnterDefault: false, // Should autoEnter be enabled by default?
	fullScreenDefault: false, // Should fullScreen be enabled by default?
	ramDefault: 1024, // Default RAM amount (0 for auto)

	// Custom JRE config (!!! DON'T CHANGE !!!)
	jvmMustdie32Dir: "jre-8u60-win32", jvmMustdie64Dir: "jre-8u60-win64",
	jvmLinux32Dir: "jre-8u60-linux32", jvmLinux64Dir: "jre-8u60-linux64",
	jvmMacOSXDir: "jre-8u60-macosx", jvmUnknownDir: "jre-8u60-unknown"
};

// ====== DON'T TOUCH! ====== //
var dir = IOHelper.HOME_DIR.resolve(config.dir);
if(!IOHelper.isDir(dir)) {
	java.nio.file.Files.createDirectory(dir);
}
var updatesDir = dir.resolve("updates");
if(!IOHelper.isDir(updatesDir)) {
	java.nio.file.Files.createDirectory(updatesDir);
}
