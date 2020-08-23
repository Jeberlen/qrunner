# qrunner
A simple QR code reader that executes html and javascript in a WebView

The idea spawned by me (Joacim Eberlen) wanting to run applications, by scanning a QR code. 
With this application others can build simple html/js applications. After minifying them and encoding them into a QR code the users can share them. As web views do not have full browser capabilities QRunner should not introduce any security issues to a system. 

Sample QR code is located in *app/sampledata/*
Sample html is located in *app/sampledata/* (zero effort canvas drawing)

#### What should be done?

Make sure business logic is contained in their correct classes, write tests and instrumented tests. 
Re-design, as this was just a quick-and-dirty design it can be done better. 
