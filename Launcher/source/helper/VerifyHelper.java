package launcher.helper;

import java.util.Map;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import launcher.LauncherAPI;

public final class VerifyHelper {
	@LauncherAPI public static final IntPredicate POSITIVE = i -> i > 0;
	@LauncherAPI public static final IntPredicate NOT_NEGATIVE = i -> i >= 0;
	@LauncherAPI public static final LongPredicate L_POSITIVE = l -> l > 0;
	@LauncherAPI public static final LongPredicate L_NOT_NEGATIVE = l -> l >= 0;
	@LauncherAPI public static final Predicate<String> NOT_EMPTY = s -> !s.isEmpty();
	@LauncherAPI public static final Pattern USERNAME_PATTERN = Pattern.compile("[a-zA-Z0-9-_\\.]{1,16}");

	private VerifyHelper() {
	}

	@LauncherAPI
	public static <K, V> V getMapValue(Map<K, V> map, K key, String error) {
		return verify(map.get(key), v -> v != null, error);
	}

	@LauncherAPI
	public static boolean isValidIDName(String name) {
		return !name.isEmpty() && name.length() <= 255 && name.chars().allMatch(VerifyHelper::isValidIDNameChar);
	}

	@LauncherAPI
	public static boolean isValidIDNameChar(int ch) {
		return ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z' || ch >= '0' && ch <= '9' || ch == '-' || ch == '_';
	}

	@LauncherAPI
	public static boolean isValidUsername(CharSequence username) {
		return USERNAME_PATTERN.matcher(username).matches();
	}

	@LauncherAPI
	public static IntPredicate range(int min, int max) {
		return i -> i >= min && i <= max;
	}

	@LauncherAPI
	public static <T> T verify(T object, Predicate<T> predicate, String error) {
		if (predicate.test(object)) {
			return object;
		}
		throw new IllegalArgumentException(error);
	}

	@LauncherAPI
	public static double verifyDouble(double d, DoublePredicate predicate, String error) {
		if (predicate.test(d)) {
			return d;
		}
		throw new IllegalArgumentException(error);
	}

	@LauncherAPI
	public static String verifyIDName(String name) {
		return verify(name, VerifyHelper::isValidIDName, String.format("Invalid name: '%s'", name));
	}

	@LauncherAPI
	public static int verifyInt(int i, IntPredicate predicate, String error) {
		if (predicate.test(i)) {
			return i;
		}
		throw new IllegalArgumentException(error);
	}

	@LauncherAPI
	public static long verifyLong(long l, LongPredicate predicate, String error) {
		if (predicate.test(l)) {
			return l;
		}
		throw new IllegalArgumentException(error);
	}

	@LauncherAPI
	public static String verifyUsername(String username) {
		return verify(username, VerifyHelper::isValidUsername, String.format("Invalid username: '%s'", username));
	}
}
