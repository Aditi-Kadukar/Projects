"use client";

import { SignInButton, SignUpButton, UserButton, useUser, Authenticated, Unauthenticated } from '@clerk/nextjs'
//import {  } from 'convex/react'
import React from 'react'
import { useMutation } from "convex/react";
import { api } from "../convex/_generated/api"; // adjust path if needed
import { useEffect } from "react";

const Header = () => {
  const { isSignedIn } = useUser();
  const storeUser = useMutation(api.users.store);

  useEffect(() => {
    if (isSignedIn) {
      storeUser();
    }
  }, [isSignedIn]);

  return (
    <div className="fixed top-6 left-1/2 transform -translate-x-1/2 z-50 w-full max-w-3xl px-4">
        <div className='backdrop-blur-md bg-white/10 border border-white/20 rounded-full px-4 sm:px-6 md:px-8 py-3 flex items-center justify-between gap-2'>
            <Unauthenticated>
                <SignInButton />
                <SignUpButton>
                <button className="bg-[#6c47ff] text-ceramic-white rounded-full font-medium text-sm sm:text-base h-10 sm:h-12 px-4 sm:px-5 cursor-pointer">
                    Sign Up
                </button>
                </SignUpButton>
            </Unauthenticated>
            <Authenticated>
                <UserButton />
            </Authenticated>
        </div>
    </div>
  )
}

export default Header;