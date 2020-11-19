package com.honggv.flutter_rtmp_live_plugin.listener;


import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.pili.pldroid.player.PLMediaPlayer;

import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.MethodChannel;
import com.honggv.flutter_rtmp_live_plugin.enums.PlayerCallBackNoticeEnum;

/**
 * rtmp播放监听器
 */
public class RtmpPlayerListener implements PLMediaPlayer.OnPreparedListener, PLMediaPlayer.OnCompletionListener, PLMediaPlayer.OnErrorListener, PLMediaPlayer.OnInfoListener, PLMediaPlayer.OnBufferingUpdateListener, PLMediaPlayer.OnVideoSizeChangedListener, PLMediaPlayer.OnSeekCompleteListener {
    /**
     * 日志标签
     */
    private static final String TAG = RtmpPlayerListener.class.getName();

    /**
     * 监听器回调的方法名
     */
    private final static String LISTENER_FUNC_NAME = "onPlayerListener";

    /**
     * 全局上下文
     */
    private Context context;

    /**
     * 通信管道
     */
    private MethodChannel channel;

    public RtmpPlayerListener(Context context, MethodChannel channel) {
        this.context = context;
        this.channel = channel;
    }

    /**
     * 调用监听器
     *
     * @param type   类型
     * @param params 参数
     */
    private void invokeListener(final PlayerCallBackNoticeEnum type, final Object params) {
        Map<String, Object> resultParams = new HashMap<>(2, 1);
        resultParams.put("type", type);
        resultParams.put("params", params == null ? null : JSON.toJSONString(params));
        channel.invokeMethod(LISTENER_FUNC_NAME, JSON.toJSONString(resultParams));
    }

    @Override
    public void onBufferingUpdate(PLMediaPlayer plMediaPlayer, int i) {

    }

    @Override
    public void onCompletion(PLMediaPlayer plMediaPlayer) {
        invokeListener(PlayerCallBackNoticeEnum.Completion, null);
    }

    @Override
    public boolean onError(PLMediaPlayer plMediaPlayer, int i) {
        invokeListener(PlayerCallBackNoticeEnum.Error, i);
        return false;
    }

    @Override
    public boolean onInfo(PLMediaPlayer plMediaPlayer, int i, int i1) {
        invokeListener(PlayerCallBackNoticeEnum.Info, i);
        return false;
    }

    @Override
    public void onPrepared(PLMediaPlayer plMediaPlayer) {
        invokeListener(PlayerCallBackNoticeEnum.Prepared, null);
    }

    @Override
    public void onSeekComplete(PLMediaPlayer plMediaPlayer) {

    }

    @Override
    public void onVideoSizeChanged(PLMediaPlayer plMediaPlayer, int i, int i1, int i2, int i3) {
        Map<String, Object> params = new HashMap<>(2, 1);
        params.put("width", i);
        params.put("height", i1);
        invokeListener(PlayerCallBackNoticeEnum.VideoSizeChanged, params);
    }
}
