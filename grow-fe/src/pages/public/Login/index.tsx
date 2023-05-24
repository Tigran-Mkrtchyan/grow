import React from 'react';
import { Form } from 'antd';
import { Password, TextField } from 'components/Base/Input';
import Button from 'components/Base/Button';

const Login = () => {
  return (
    <div
      style={{
        height: '100vh',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
      }}
    >
      <div
        style={{
          width: '300px',
          display: 'flex',
          flexDirection: 'column',
          justifyContent: 'center',
          alignItems: 'center',
        }}
      >
        <Form
          name="login"
          layout="vertical"
          requiredMark={false}
          onFinish={values => console.log(values)}
        >
          <Form.Item label="Email" name="email" rules={[{ required: true }]}>
            <TextField />
          </Form.Item>
          <Form.Item
            label="Password"
            name="password"
            rules={[{ required: true }]}
          >
            <Password />
          </Form.Item>
          <Button htmlType="submit">Login</Button>
        </Form>
      </div>
    </div>
  );
};

export default Login;
