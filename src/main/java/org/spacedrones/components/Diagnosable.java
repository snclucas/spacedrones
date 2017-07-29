package org.spacedrones.components;

import org.spacedrones.status.SystemStatus;

public interface Diagnosable {
	SystemStatus runDiagnostics(int level);
}
