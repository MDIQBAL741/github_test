import React, { useState } from "react";
 
import { Col } from "react-bootstrap";
 
import Form from "react-bootstrap/Form";
 
const Textarea = function ({
  label,
 
  placeholder,
 
  handleChange,
 
  isError,
 
  format,
 
  value,
 
  type,
 
  maxlength,
 
  errorMessage,
 
  pattern,
 
  rows,
 
  cols,
}) {
  const [errorFlag, setErrorFlag] = useState(isError);
 
  const [errorMsg, setErrorMsg] = useState(errorMessage);
 
  console.log(value);
 
  const validateField = () => {
    if (value === "" || !value) {
      setErrorFlag(true);
 
      setErrorMsg(label + " should not be empty");
    }
  };
 
  const onTextChange = (e) => {
    let val = true;
 
    if (type === "alpha") {
      val = /^[a-z A-Z]+$/.test(e.target.value);
    }
 
    if (type === "num") {
      val = /^[0-9]+$/.test(e.target.value);
    }
 
    setErrorFlag(false);
 
    setErrorMsg("");
 
    if (val) handleChange(e.target.value);
  };
 
  return (
    <Form>
      <Form.Group className="mb-3" controlId="formGroupText">
        <Form.Label>{label}</Form.Label>
 
        <Form.Control
          as="textarea"
          type={format}
          placeholder={placeholder}
          onChange={(e) => onTextChange(e)}
          onBlur={validateField}
          value={value}
          maxLength={maxlength}
          pattern={pattern}
          rows={rows}
          cols={cols}
        />
 
        {errorFlag && <span>{errorMsg}</span>}
      </Form.Group>
    </Form>
  );
};
 
export default Textarea;
 