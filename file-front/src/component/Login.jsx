import React from 'react';
import { Button } from 'react-bootstrap';

function SocialLogin() {
  const handleGoogleLogin = () => {
    window.location.href = 'http://localhost:8080/oauth2/authorization/google'; 
  };

  const handleGitHubLogin = () => {
    window.location.href = 'http://localhost:8080/oauth2/authorization/github'; 
  };
  
  return (
    <div>
      <Button className='mb-4 mt-4 ' size='lg' onClick={handleGoogleLogin} variant="danger">
        Login with Google
      </Button>
      <Button className='mb-4 mt-4' size='lg' onClick={handleGitHubLogin} variant="dark">
        Login with GitHub
      </Button>
    </div>
  );
}

export default SocialLogin;
