
#### 横向循环滑动广告展示效果：

![效果图1](gif/device-2019-06-17-133439.gif)

gradle使用：

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

```
		dependencies {
	        implementation 'com.github.Johnjson:MarqueeEffect:v1.0.0'
	}

```

maven使用：

```
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```

```
	<dependency>
	    <groupId>com.github.Johnjson</groupId>
	    <artifactId>MarqueeEffect</artifactId>
	    <version>v1.0.0</version>
	</dependency>

```

项目中：

```

  <com.click.marquee_lib.view.MarqueeRecyclerViews
        android:id="@+id/mRv_View"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:background="#0eff39" />

```

```
public class MainActivity extends AppCompatActivity implements CallBackItem {

    private MarqueeRecyclerViews marqueeRecyclerViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        marqueeRecyclerViews = findViewById(R.id.mRv_View);
        initData();
    }


    private void initData() {
        ArrayList<String> data = new ArrayList<String>();
        String[] str = new String[]{"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA1111111", "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB222222", "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC33333"};
        for (int i = 0; i < 3; i++) {
            data.add(i, str[i]);
        }
        marqueeRecyclerViews.setData(data, this);
        marqueeRecyclerViews.startAnimation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopAnimation();
    }

    private void startAnimation() {
        if (marqueeRecyclerViews != null) {
            marqueeRecyclerViews.startAnimation();
        }
    }

    private void stopAnimation() {
        if (marqueeRecyclerViews != null) {
            marqueeRecyclerViews.stopAnimation();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startAnimation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopAnimation();
    }

    @Override
    public void callBackPosition(int position, Object o) {

        Toast.makeText(this, "点击位置 " + position + " 返回数据 " + (String) o, Toast.LENGTH_LONG).show();
    }

}
```

参考：[无限循环RecyclerView的完美实现方案](https://www.cnblogs.com/1157760522ch/p/10998401.html)

