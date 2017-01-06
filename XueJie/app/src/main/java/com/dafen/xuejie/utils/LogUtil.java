package com.dafen.xuejie.utils;

import android.os.SystemClock;
import android.util.Log;

import com.dafen.xuejie.constant.GlobalConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/** Logging helper class. */
public class LogUtil {
	public static String TAG = GlobalConfig.APP_EN_NAME;
	public static boolean DEBUG = GlobalConfig.IS_DEBUG;

	/**
	 * Customize the log tag for your application<br />
	 * Enable the log property for your tag before starting your app: <br />
	 * {@code adb shell setprop log.tag.&lt;tag&gt;}
	 */
	public static void setTag(String tag) {
		d("Changing log tag to %s", tag);
		TAG = tag;
		// Reinitialize the DEBUG "constant"
		DEBUG = Log.isLoggable(TAG, Log.VERBOSE);
	}

	public static void v(String format, Object... args) {
		if (DEBUG) {
			Log.v(TAG, buildMessage(format, args));
		}
	}

	public static void i(String format, Object... args) {
		if (DEBUG) {
			Log.i(TAG, buildMessage(format, args));
		}
	}

	public static void d(String format, Object... args) {
		if (DEBUG)
		Log.d(TAG, buildMessage(format, args));
	}

	public static void w(String format, Object... args) {
		if (DEBUG)
			Log.w(TAG, buildMessage(format, args));
	}
	public static void e(String format, Object... args) {
		if (DEBUG)
		Log.e(TAG, buildMessage(format, args));
	}

	public static void e(Throwable tr, String format, Object... args) {
		if (DEBUG)
		Log.e(TAG, buildMessage(format, args), tr);
	}

	public static void wtf(String format, Object... args) {
		if (DEBUG)
		Log.wtf(TAG, buildMessage(format, args));
	}

	public static void wtf(Throwable tr, String format, Object... args) {
		if (DEBUG)
		Log.wtf(TAG, buildMessage(format, args), tr);
	}

	/**
	 * Formats the caller's provided message and prepends useful info like calling thread ID and method name.
	 */
	private static String buildMessage(String format, Object... args) {
		String msg = (args == null) ? format : String.format(Locale.US, format, args);
		StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
		String caller = "<unknown>";
		// It will be at least two frames up, so start there.
		for (int i = 3; i < trace.length; i++) {
			Class<?> clazz = trace[i].getClass();
			if (!clazz.equals(LogUtil.class)) {
				String callingClass = trace[i].getClassName();
				callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1);
				callingClass = callingClass.substring(callingClass.lastIndexOf('$') + 1);
				caller = trace[i].toString();
				break;
			}
		}


		long threadId = Thread.currentThread().getId();

		String threadStr = String.format(
				"#LOG-%s[%s]",
				threadId == 1 ? "MainThread" : "SubThread",
				threadId
		);

		return TextFormatUtils.format2TableStr(
				threadStr,
				caller,
				msg
		);

//		return String.format(Locale.US, "[%d] %s: %s", threadId, caller, msg);
	}

	/**
	 * A simple event log with records containing a name, thread ID, and timestamp.
	 */
	static class MarkerLog {
		public static final boolean ENABLED = LogUtil.DEBUG;
		/** Minimum duration from first marker to last in an marker log to warrant logging. */
		private static final long MIN_DURATION_FOR_LOGGING_MS = 0;

		private static class Marker {
			public final String name;
			public final long thread;
			public final long time;

			public Marker(String name, long thread, long time) {
				this.name = name;
				this.thread = thread;
				this.time = time;
			}
		}

		private final List<Marker> mMarkers = new ArrayList<Marker>();
		private boolean mFinished = false;

		/** Adds a marker to this log with the specified name. */
		public synchronized void add(String name, long threadId) {
			if (mFinished) {
				throw new IllegalStateException("Marker added to finished log");
			}
			mMarkers.add(new Marker(name, threadId, SystemClock.elapsedRealtime()));
		}

		/**
		 * Closes the log, dumping it to logcat if the time difference between the first and last markers is greater than
		 * {@link #MIN_DURATION_FOR_LOGGING_MS}.
		 * 
		 * @param header
		 *            Header string to print above the marker log.
		 */
		public synchronized void finish(String header) {
			mFinished = true;
			long duration = getTotalDuration();
			if (duration <= MIN_DURATION_FOR_LOGGING_MS) {
				return;
			}
			long prevTime = mMarkers.get(0).time;
			d("(%-4d ms) %s", duration, header);
			for (Marker marker : mMarkers) {
				long thisTime = marker.time;
				d("(+%-4d) [%2d] %s", (thisTime - prevTime), marker.thread, marker.name);
				prevTime = thisTime;
			}
		}

		@Override
		protected void finalize() throws Throwable {
			// Catch requests that have been collected (and hence end-of-lifed)
			// but had no debugging output printed for them.
			if (!mFinished) {
				finish("CompatRequest on the loose");
				e("Marker log finalized without finish() - uncaught exit point for request");
			}
		}

		/** Returns the time difference between the first and last events in this log. */
		private long getTotalDuration() {
			if (mMarkers.size() == 0) {
				return 0;
			}
			long first = mMarkers.get(0).time;
			long last = mMarkers.get(mMarkers.size() - 1).time;
			return last - first;
		}
	}
}