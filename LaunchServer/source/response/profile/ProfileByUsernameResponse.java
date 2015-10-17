package launchserver.response.profile;

import java.io.IOException;
import java.util.UUID;

import launcher.helper.LogHelper;
import launcher.helper.VerifyHelper;
import launcher.serialize.HInput;
import launcher.serialize.HOutput;
import launchserver.LaunchServer;
import launchserver.response.Response;

public final class ProfileByUsernameResponse extends Response {
	public ProfileByUsernameResponse(LaunchServer server, HInput input, HOutput output) {
		super(server, input, output);
	}

	@Override
	public void reply() throws IOException {
		String username = VerifyHelper.verifyUsername(input.readASCII(16));
		LogHelper.subDebug("Username: " + username);
		writeProfile(server, output, username);
	}

	public static void writeProfile(LaunchServer server, HOutput output, String username) throws IOException {
		UUID uuid = server.getConfig().authHandler.usernameToUUID(username);
		if (uuid == null) {
			output.writeBoolean(false);
			return;
		}

		// Write profile
		output.writeBoolean(true);
		ProfileByUUIDResponse.getProfile(server, uuid, username).write(output);
	}
}
