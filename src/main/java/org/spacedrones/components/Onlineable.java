package org.spacedrones.components;

import org.spacedrones.status.SystemStatus;

public interface Onlineable {
	boolean isOnline();
	SystemStatus online();
}
