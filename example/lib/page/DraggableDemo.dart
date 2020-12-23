import 'package:flutter/material.dart';
import 'package:flutter_rtmp_live_plugin_example/page/DraggableWidget.dart';


class DraggableDemo extends StatefulWidget {
  DraggableDemo({Key key}) : super(key: key);
  @override
  _DraggableDemoState createState() {
    return _DraggableDemoState();
  }
}

class _DraggableDemoState extends State<DraggableDemo> {
  Color  _draggablecolor=Colors.grey;
  void initState() {
    super.initState();
  }
  @override
  void dispose() {
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      body: Stack(
        children: [
          DraggableWidget(
            offset: Offset(80.0,80.0),
            widgetcolor: Colors.tealAccent,
          ),
          DraggableWidget(
            offset: Offset(180.0,80.0),
            widgetcolor: Colors.redAccent,
          ),
          Center(
            child: DragTarget(
              onAccept: (Color color){
                _draggablecolor=color;
              },
              builder: (context,candidateData,rejectedData){
                return Container(
                  width: 200,
                  height: 200,
                  color: _draggablecolor,
                );
              },
            ),
          )
        ],
      ),
    );
  }
}