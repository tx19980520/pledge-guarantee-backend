package wd.pledge.guarantee.util;

import org.springframework.lang.Nullable;

public enum AlertType {
  MOVE(0),

  SHAKE(1);

  int type;

  AlertType(int type) {
    this.type = type;
  }

  public int getType() {
    return type;
  }

  @Nullable
  public static AlertType resolve(int t) {
    for (AlertType type : values()) {
      if (type.type == t) {
        return type;
      }
    }
    return null;
  }
}
