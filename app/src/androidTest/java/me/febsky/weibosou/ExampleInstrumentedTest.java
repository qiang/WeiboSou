package me.febsky.weibosou;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import me.febsky.weibosou.common.ApiResponse;
import me.febsky.weibosou.common.RequestCallback;
import me.febsky.weibosou.util.VolleyHelper;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws InterruptedException {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        VolleyHelper.requestGsonBean("http://172.18.19.190:8080/get_app_proto_hotel", new TypeToken<ApiResponse<TestBean>>() {
        }.getType(), new RequestCallback<TestBean>() {

            @Override
            public void beforeRequest() {

            }

            @Override
            public void requestError(Exception e) {

            }

            @Override
            public void requestComplete() {

            }

            @Override
            public void requestSuccess(TestBean data) {

                Log.d("Q_M", "" + data);
            }
        });

        Thread.sleep(1111111);
    }
}
