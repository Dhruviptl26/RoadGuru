# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Keep the licences activity class
-keep class com.example.gptlogin.licences {
    *;
}

# Keep all activities
-keep class * extends android.app.Activity {
    *;
}

# Keep all services
-keep class * extends android.app.Service {
    *;
}

# Keep all broadcast receivers
-keep class * extends android.content.BroadcastReceiver {
    *;
}

# Keep fields in classes implementing Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Preserve line number information for stack traces
-keepattributes SourceFile,LineNumberTable

# Keep classes used for JSON parsing
-keep class com.example.gptlogin.model.** { *; }

