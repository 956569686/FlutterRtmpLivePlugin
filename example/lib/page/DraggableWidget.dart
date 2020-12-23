import 'package:flutter/material.dart';
import 'package:flutter_rtmp_live_plugin_example/page/player.dart';

class DraggableWidget extends StatefulWidget {
  final Offset offset;
  final Color widgetcolor;
  DraggableWidget({Key key,this.offset,this.widgetcolor}) : super(key: key);
  @override
  _DraggableWidgetState createState() {
    return _DraggableWidgetState();
  }
}
class _DraggableWidgetState extends State<DraggableWidget> {
  Offset offset=Offset(0.0, 0.0);
  @override
  void initState() {
    super.initState();
    offset=widget.offset;
  }
  @override
  void dispose() {
    super.dispose();
  }
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Positioned(
      left: offset.dx,
      top: offset.dy,
      child: Draggable(
        data: widget.widgetcolor,
        child: Container(
          width: 100.0,
          height: 100.0,
          color: widget.widgetcolor,
          child: PlayerPage(),
        ),
        feedback: Container(
          width: 110.0,
          height: 110.0,
          color: widget.widgetcolor.withOpacity(0.5),
          child: PlayerPage(),
        ),
        onDraggableCanceled: (Velocity velocity, Offset offset){
          setState(() {
            this.offset=offset;
          });
        },
      ),
    );
  }
}