import React from "react";

import Button from "react-bootstrap/Button";

function ButtonComponent({ variant, type, label,disabled,handleClick}) {
  return (
    <div>
      <Button variant={variant} type={type} disabled={disabled} onClick={handleClick}>
        {label}
      </Button>{" "}
    </div>
  );
}

export default ButtonComponent;
