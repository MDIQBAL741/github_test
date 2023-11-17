import React, { useState } from "react";
import Form from "react-bootstrap/Form";
const Textbox = function ({
  label,
  name,
  placeholder,
  handleChange,
  isError,
  format,
  value,
  type,
  maxlength,
  errorMessage,
  pattern,
}) 
{
  const [errorFlag, setErrorFlag] = useState(isError);
  const [errorMsg, setErrorMsg] = useState(errorMessage);
  // console.log(value);
  const validateField = () => {
    if (value === "" || !value) {
      setErrorFlag(true);
      setErrorMsg(label + " should not be empty");
    }
  };
  const onTextChange = (e) => {
    let val = true;

    if (type === "alpha") {
      val = /^[a-zA-Z]+$/.test(e.target.value);
    }

    if (type === "num") {
      val = /^[0-9]+$/.test(e.target.value);
    }
    if (type === "email") {
      val = /^[@mphasis.com || @gmail.com || @outlook.com]+$/.test( e.target.value );
    }
    if (type === "custom") {
      val = /^[A-Z]{5}[0-9]{4}[A-Z]{1}$/.test(e.target.value);
    
    

    }
    setErrorFlag(false);
    setErrorMsg("");
    if (val || e.target.value ==="") handleChange(e.target.value);
  };
  return (
    <Form>
      <Form.Group className="mb-3" controlId="formGroupText">
        <Form.Label>{label}</Form.Label>

        <Form.Control
          
          type={format}
          name={name}
          placeholder={placeholder}
          onChange={(e) => onTextChange(e)}
          onBlur={validateField}
          value={value}
          maxLength={maxlength}
          pattern={pattern}
          

        />

        {errorFlag && <span>{errorMsg}</span>}
      </Form.Group>
    </Form>
  );
};

export default Textbox;
