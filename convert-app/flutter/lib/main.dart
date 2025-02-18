import 'package:flutter/material.dart';
import 'screens/todo_list_screen.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Todo List App',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        fontFamily: 'Geist',
      ),
      home: const TodoListScreen(),
    );
  }
}
