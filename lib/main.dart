import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(const MyApp());

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String batteryLevel = 'Waiting...';
  static const batteryChannel = MethodChannel('com.mahi/battery');

  Future getBatteryLevel() async {
    final arguments = {'name': 'Sarah Abs'};

    final String newBatteryLevel = await batteryChannel.invokeMethod(
      'getBatteryLevel',
      arguments,
    );

    setState(() {
      batteryLevel = '$newBatteryLevel';
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Material App',
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Material App Bar'),
        ),
        body: Column(children: [
          Text(batteryLevel),
           ElevatedButton(
            child: Text("get Battery level"),
            onPressed: getBatteryLevel,
          )
        ]),
      ),
    );
  }
}



// String batteryLevel = 'Waiting...';
//   static const batteryChannel = MethodChannel('mahidev.com/battery');
//   Future getBatteryLevel() async 
//   {
//   //  final arguments = {'name': 'Sarah Abs'};
//      String newBatteryLevel =
//         await batteryChannel.invokeMethod('getBatteryLevel');//, arguments);
//     setState(() => batteryLevel = newBatteryLevel);
//   }